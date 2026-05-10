package com.example.kelompok

import android.view.LayoutInflater
import android.view.View // PENTING: Untuk mengatur Visibility (GONE/VISIBLE)
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ProdukAdapter(
    // Inovasi: Parameter untuk membedakan tampilan Katalog vs Keranjang
    private val isKeranjang: Boolean = false,
    private val onClick: (Produk) -> Unit,
    private val onLongClick: (Produk) -> Unit
) : ListAdapter<Produk, ProdukAdapter.ProdukViewHolder>(ProdukDiffCallback()) {

    class ProdukViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduk: ImageView = view.findViewById(R.id.ivProduk)
        val tvNama: TextView = view.findViewById(R.id.tvNamaProduk)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaProduk)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
        val btnTambah: Button = view.findViewById(R.id.btnTambah)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdukViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produk, parent, false)
        return ProdukViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdukViewHolder, position: Int) {
        val produk = getItem(position)

        // Mengisi data produk ke UI
        holder.tvNama.text = produk.nama
        holder.tvHarga.text = "Rp ${produk.harga}"
        holder.tvRating.text = "⭐ ${produk.rating}"
        holder.ivProduk.setImageResource(produk.gambar)

        // --- INOVASI: LOGIKA UPDATE OUTPUT ---
        // Jika sedang di halaman keranjang, sembunyikan tombol "Tambah"
        if (isKeranjang) {
            holder.btnTambah.visibility = View.GONE
        } else {
            holder.btnTambah.visibility = View.VISIBLE
        }

        // 1. Klik Item untuk lihat detail produk
        holder.itemView.setOnClickListener {
            onClick(produk)
        }

        // 2. Klik Tombol TAMBAH KE KERANJANG (Hanya untuk Katalog Utama)
        holder.btnTambah.setOnClickListener {
            // Memasukkan produk ke list global di DataProduk
            DataProduk.listBaruDitambahkan.add(produk)

            Toast.makeText(
                holder.itemView.context,
                "${produk.nama} berhasil masuk keranjang!",
                Toast.LENGTH_SHORT
            ).show()
        }

        // 3. Klik Tahan (Long Click) untuk fitur hapus
        holder.itemView.setOnLongClickListener {
            onLongClick(produk)
            true
        }

        // Jalankan animasi saat item muncul di layar
        setAnimation(holder.itemView)
    }

    // Fungsi untuk efek animasi halus pada setiap item list
    private fun setAnimation(viewToAnimate: View) {
        val animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.item_animation)
        viewToAnimate.startAnimation(animation)
    }
}