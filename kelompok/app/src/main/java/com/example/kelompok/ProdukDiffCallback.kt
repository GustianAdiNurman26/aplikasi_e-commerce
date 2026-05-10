package com.example.kelompok

import androidx.recyclerview.widget.DiffUtil

class ProdukDiffCallback : DiffUtil.ItemCallback<Produk>() {
    override fun areItemsTheSame(oldItem: Produk, newItem: Produk): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Produk, newItem: Produk): Boolean {
        return oldItem == newItem
    }
}