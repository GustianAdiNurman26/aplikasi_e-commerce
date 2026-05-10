package com.example.kelompok

data class Produk(
    val id: Int,
    val nama: String,
    val harga: Int,
    val rating: Float,
    val gambar: Int, // Ini untuk menyimpan resource ID (R.drawable.nama_gambar)
    val deskripsi: String
)