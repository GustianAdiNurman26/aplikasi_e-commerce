package com.example.kelompok

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ProdukAdapter
    private lateinit var rvProduk: RecyclerView
    private lateinit var btnLihatKeranjang: ExtendedFloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi View
        rvProduk = findViewById(R.id.rvProduk)
        btnLihatKeranjang = findViewById(R.id.btnCekBarangBaru)

        // 2. Setup Adapter
        // isKeranjang = false agar tombol "TAMBAH" muncul di katalog utama
        adapter = ProdukAdapter(
            isKeranjang = false,
            onClick = { produk -> showDetailDialog(produk) },
            onLongClick = { /* Tidak ada aksi hapus di sini */ }
        )

        // 3. Setup RecyclerView (Tampilan 2 Kolom)
        rvProduk.layoutManager = GridLayoutManager(this, 2)
        rvProduk.adapter = adapter

        // Kirim data dari object DataProduk
        adapter.submitList(DataProduk.listProduk)

        // 4. Logika Tombol Lihat Keranjang
        btnLihatKeranjang.setOnClickListener {
            if (DataProduk.listBaruDitambahkan.isEmpty()) {
                Toast.makeText(this, "Keranjang belanja lo masih kosong!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, AddedProductsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // --- PENTING: Update teks tombol keranjang saat kembali ke MainActivity ---
    override fun onResume() {
        super.onResume()
        updateCartBadge()
    }

    // Inovasi: Update jumlah barang di tombol Floating Action Button
    private fun updateCartBadge() {
        val count = DataProduk.listBaruDitambahkan.size
        if (count > 0) {
            btnLihatKeranjang.text = "Keranjang ($count)"
        } else {
            btnLihatKeranjang.text = "Keranjang"
        }
    }

    // Fungsi Pop-up Detail Produk
    private fun showDetailDialog(produk: Produk) {
        val bottomSheet = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.layout_detail_produk, null)

        val ivGambar: ImageView = view.findViewById(R.id.ivDetailGambar)
        val tvNama: TextView = view.findViewById(R.id.tvDetailNama)
        val tvHarga: TextView = view.findViewById(R.id.tvDetailHarga)
        val tvDeskripsi: TextView = view.findViewById(R.id.tvDetailDeskripsi)
        val btnBeli: Button = view.findViewById(R.id.btnBeliLangsung)

        ivGambar.setImageResource(produk.gambar)
        tvNama.text = produk.nama
        tvHarga.text = "Rp ${produk.harga}"
        tvDeskripsi.text = produk.deskripsi

        btnBeli.setOnClickListener {
            // Menambahkan barang ke keranjang global
            DataProduk.listBaruDitambahkan.add(produk)

            // Langsung update angka di tombol Dashboard
            updateCartBadge()

            Toast.makeText(this, "${produk.nama} masuk keranjang!", Toast.LENGTH_SHORT).show()
            bottomSheet.dismiss()
        }

        bottomSheet.setContentView(view)
        bottomSheet.show()
    }
}