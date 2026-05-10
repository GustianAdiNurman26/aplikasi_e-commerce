package com.example.kelompok

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog

class AddedProductsActivity : AppCompatActivity() {

    private lateinit var adapter: ProdukAdapter
    private lateinit var rvKeranjang: RecyclerView
    private lateinit var tvTotalHarga: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_added_products)

        // --- UPDATE: NAVIGASI TOOLBAR & JUDUL ---
        supportActionBar?.apply {
            title = "Keranjang Belanja" // Judul otomatis berubah
            setDisplayHomeAsUpEnabled(true) // Tombol panah back muncul
            elevation = 8f
        }

        // Inisialisasi View
        rvKeranjang = findViewById(R.id.rvBarangBaru)
        tvTotalHarga = findViewById(R.id.tvTotalHarga)
        val btnCheckout: Button = findViewById(R.id.btnCheckout)

        // Setup Adapter
        adapter = ProdukAdapter(
            isKeranjang = true,
            onClick = { produk -> showDetailDialog(produk) },
            onLongClick = { produk -> showDeleteConfirmation(produk) }
        )

        rvKeranjang.layoutManager = GridLayoutManager(this, 2)
        rvKeranjang.adapter = adapter

        // Logika Tombol Checkout
        btnCheckout.setOnClickListener {
            if (DataProduk.listBaruDitambahkan.isNotEmpty()) {
                showSuccessCheckoutDialog()
            } else {
                Toast.makeText(this, "Keranjang lo masih kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * FUNGSI KRUSIAL: Menjalankan animasi setelah data benar-benar siap
     */
    private fun runLayoutAnimation(recyclerView: RecyclerView) {
        val context = recyclerView.context
        val controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)

        recyclerView.layoutAnimation = controller

        // Menggunakan .post agar sistem menunggu data render dulu baru animasi ditembak
        recyclerView.post {
            recyclerView.adapter?.notifyDataSetChanged()
            recyclerView.scheduleLayoutAnimation()
        }
    }

    // --- LOGIKA TOMBOL BACK DI POJOK KIRI ATAS ---
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        // Transisi halus saat kembali ke depan
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right)
        return true
    }

    override fun onResume() {
        super.onResume()
        refreshData()
        // Jalankan animasi setiap kali masuk ke halaman keranjang
        runLayoutAnimation(rvKeranjang)
    }

    private fun refreshData() {
        val dataKeranjang = DataProduk.listBaruDitambahkan
        adapter.submitList(dataKeranjang.toList())

        var subtotal = 0
        for (produk in dataKeranjang) {
            subtotal += produk.harga
        }
        tvTotalHarga.text = "Rp $subtotal"
    }

    private fun showSuccessCheckoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Checkout Berhasil")
            .setMessage("Pesanan kamu sedang diproses. Keranjang akan dikosongkan.")
            .setCancelable(false)
            .setPositiveButton("Selesai") { _, _ ->
                DataProduk.listBaruDitambahkan.clear()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
            .show()
    }

    private fun showDetailDialog(produk: Produk) {
        val bottomSheet = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.layout_detail_produk, null)

        view.findViewById<TextView>(R.id.tvDetailNama).text = produk.nama
        view.findViewById<TextView>(R.id.tvDetailHarga).text = "Rp ${produk.harga}"
        view.findViewById<TextView>(R.id.tvDetailDeskripsi).text = produk.deskripsi
        view.findViewById<ImageView>(R.id.ivDetailGambar).setImageResource(produk.gambar)

        bottomSheet.setContentView(view)
        bottomSheet.show()
    }

    private fun showDeleteConfirmation(produk: Produk) {
        AlertDialog.Builder(this)
            .setTitle("Hapus Barang")
            .setMessage("Yakin ingin menghapus ${produk.nama}?")
            .setPositiveButton("Ya, Hapus") { _, _ ->
                DataProduk.listBaruDitambahkan.remove(produk)
                refreshData()
                Toast.makeText(this, "Berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}