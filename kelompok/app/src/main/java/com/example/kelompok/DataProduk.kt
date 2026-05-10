package com.example.kelompok

object DataProduk {
    // 1. List Utama (Katalog Produk)
    // Gambar sudah disesuaikan dengan file yang ada di drawable kamu
    val listProduk = listOf(
        Produk(
            id = 1,
            nama = "Sepatu Lari Ultra",
            harga = 450000,
            rating = 4.8f,
            gambar = R.drawable.sepatulari, // Sesuaikan dengan nama file di drawable
            deskripsi = "Sepatu lari ultra ringan dengan teknologi bantalan mutakhir untuk kenyamanan maksimal."
        ),
        Produk(
            id = 2,
            nama = "Tas Ransel Kanvas",
            harga = 250000,
            rating = 4.6f,
            gambar = R.drawable.tasransel,
            deskripsi = "Tas ransel berbahan kanvas premium yang kuat. Memiliki kompartemen laptop khusus."
        ),
        Produk(
            id = 3,
            nama = "Jam Tangan Digital",
            harga = 175000,
            rating = 4.4f,
            gambar = R.drawable.jamtangan_digital,
            deskripsi = "Jam tangan digital sporty dengan fitur tahan air, lampu LED, dan stopwatch."
        ),
        Produk(
            id = 4,
            nama = "Kemeja Flanel",
            harga = 185000,
            rating = 4.5f,
            gambar = R.drawable.kemeja,
            deskripsi = "Kemeja flanel lembut dengan motif kotak-kotak klasik. Cocok untuk casual streetwear."
        ),
        Produk(
            id = 5,
            nama = "Topi Baseball",
            harga = 95000,
            rating = 4.3f,
            gambar = R.drawable.topi_baseball,
            deskripsi = "Topi baseball dengan bahan katun drill berkualitas tinggi. Nyaman digunakan seharian."
        ),
        Produk(
            id = 6,
            nama = "Jaket Bomber",
            harga = 450000,
            rating = 4.7f,
            gambar = R.drawable.jaket_bomber,
            deskripsi = "Jaket bomber stylish dengan bahan parasut tahan angin dan lapisan dalam yang hangat."
        ),
        Produk(
            id = 7,
            nama = "Celana Chino",
            harga = 275000,
            rating = 4.5f,
            gambar = R.drawable.celana_chino,
            deskripsi = "Celana chino dengan potongan slim fit yang memberikan kesan jenjang dan rapi."
        ),
        Produk(
            id = 8,
            nama = "Dompet Kulit",
            harga = 210000,
            rating = 4.6f,
            gambar = R.drawable.dompet_kulit,
            deskripsi = "Dompet kulit asli dengan desain minimalis namun memiliki banyak slot kartu."
        ),
        Produk(
            id = 9,
            nama = "Kacamata Hitam",
            harga = 150000,
            rating = 4.2f,
            gambar = R.drawable.kacamata_hitam,
            deskripsi = "Kacamata hitam dengan lensa polarisasi untuk melindungi mata dari sinar UV."
        ),
        Produk(
            id = 10,
            nama = "Sandal Gunung",
            harga = 125000,
            rating = 4.4f,
            gambar = R.drawable.sendal_gunung,
            deskripsi = "Sandal gunung tangguh dengan sol karet anti slip yang kuat."
        )
    )

    // 2. List penampung barang yang ditambahkan ke keranjang
    val listBaruDitambahkan = arrayListOf<Produk>()
}