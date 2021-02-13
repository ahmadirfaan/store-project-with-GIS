



## Project Description

​	Proyek ini merupakan pembuatan REST API dengan meng-analogikan sebuah toko yang dapat menyimpan data barang (*item*), stok barang (*stock*), satuan barang(*unit*), transaksi(*transaction*), dan lokasi toko(*store location*).  Dengan mendapatkan koordinat user API akan mengembalikan jarak lokasi antara user dan lokasi toko terdekat. Untuk dapat menjalankan proyek ini diperlukan : 

- Spring Boot dengan Java 11

- PostgreSQL dengan ekstensi PostGIS untuk mengeksekusi data spasial (lokasi toko)

- Maven

- Pembuatan database SubCounty dan Store_Location di PostgreSQL, yaitu pada aplikasi QGIS lakukan export *shapefile* ke *database* dan pilih PostgreSQL sehingga data-data lokasi titik dan area (subcounty) akan otomatis berubah menjadi database di PostgreSQL dengan tipe data *Point* dan *Multipoint*. Setelah merubah data tersebut dapat dilakukan fungsi-fungsi eksekusi query pada database.

  



## REST API Description

- **Unit**

  - Mendapatkan semua *unit* : `GET http://localhost:8081/units`

  - Mendapatkan *unit* berdasarkan id : `GET http://localhost:8081/units/{id}`

  - Menambahkan *unit* baru : `POST http://localhost:8081/units` 

    dengan contoh :

    ```json
    {
      "code" : "ml" ,
      "description" : "Mililiter"
    }
    ```

    

  - Mengedit *unit* berdasarkan id : `PUT http://localhost:8081/units/{id}`

  - Menghapus *unit* berdasarkan id : `DELETE http://localhost:8081/units/{id}`

- **Item**

  - Mendapatkan semua *items* : `GET http://localhost:8081/items`

  - Mendapatkan  *item* berdasarkan id  : `GET http://localhost:8081/items/{id}`

  - Menambahkan *item* baru   : `POST http://localhost:8081/items`

    dengan contoh :

    ```json
    {
        "name" : "Jam Tangan Swiss Army",
        "unitId" : 1,
      "price" : 500000
    }
    ```

    

  - Mengedit *item* berdasarkan Id: `PUT http://localhost:8081/items/{id}`

  - Menghapus *item* berdasarkan id: `DELETE http://localhost:8081/items/{id}`

- **Stock**

  - Mendapatkan semua *stocks* : `GET http://localhost:8081/stocks`

  - Mendapatkan  *stock* berdasarkan id : `GET http://localhost:8081/items/{id}`

  - Menambahkan *stock* baru : `POST http://localhost:8081/items`

    dengan contoh :

    ```json
    {
        "itemId" : 1,
      "quantity" : 100
    }
    ```

    

  - Mengedit *stock* berdasarkan Id: : `PUT http://localhost:8081/items/{id}`

  - Menghapus *stock* berdasarkan id : `DELETE http://localhost:8081/items/{id}`

- **Transaction**

  - Mendapatkan daftar *transactions* : `GET http://localhost:8081/transactions`

  - Mendapatkan  *transactions* berdasarkan id  : `GET http://localhost:8081/transactions/{id}`

  - Menambah *transactions* baru : `POST http://localhost:8081/transactions`

    dengan contoh :

    ```json
    {
        "detailTransactionList": [
            {
                "quantity": 100,
                "itemId": 1
            },
            {
                "quantity": 100,
                "itemId": 2
            },
            {
                "quantity": 100,
                "itemId": 4
            }
        ]
    }
    ```

    

  - menghapus *transactions* berdasarkan id : `DELETE http://localhost:8081/items/{id}`

- **Store Location**

  - Mendapatkan lokasi toko berdasarkan *sub-county* atau distrik id sehingga yang didapatkan hanya lokasi toko di daerah tersebut :

    `GET http://localhost:8081/storelocation/withinsubcounty?id=1`, 

    dengan menggunakan POSTMAN maka didapatkan hasil-nya sebagai berikut :

    ```
    {
        "code": 200,
        "message": null,
        "data": [
            {
                "id": 2,
                "name": "Enigma Mart Cabang 1",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.7962817,
                        -1.2941
                    ]
                },
                "distance": null
            },
            {
                "id": 25,
                "name": "Enigma Mart Cabang 24",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.8107235,
                        -1.2782734
                    ]
                },
                "distance": null
            },
            {
                "id": 37,
                "name": "Enigma Mart Cabang 36",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.7916908,
                        -1.293483
                    ]
                },
                "distance": null
            },
            {
                "id": 38,
                "name": "Enigma Mart Cabang 37",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.7964866,
                        -1.2939955
                    ]
                },
                "distance": null
            }
        ],
        "timestamp": "2021-02-13T14:04:48.159362"
    }
    ```

  - Mendapatkan 5 lokasi toko berdasarkan jarak dari lokasi yang diinginkan :

    `GET http://localhost:8081/storelocation/nearbystore?userlocation=36.8219,-1.2921`, 

    dengan menggunakan POSTMAN maka didapatkan hasil-nya sebagai berikut :

    ```json
    {
        "code": 200,
        "message": null,
        "data": [
            {
                "id": 24,
                "name": "Enigma Mart Cabang 23",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.8155523,
                        -1.2796122
                    ]
                },
                "distance": 1554.9449733432957
            },
            {
                "id": 1,
                "name": "Enigma Mart Pusat",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.8050851,
                        -1.2960949
                    ]
                },
                "distance": 1918.4065743461151
            },
            {
                "id": 25,
                "name": "Enigma Mart Cabang 24",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.8107235,
                        -1.2782734
                    ]
                },
                "distance": 1973.4570715063412
            },
            {
                "id": 59,
                "name": "Enigma Mart Cabang 58",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.8341362,
                        -1.3070192
                    ]
                },
                "distance": 2141.7740343429873
            },
            {
                "id": 66,
                "name": "Enigma Mart Cabang 65",
                "geom": {
                    "type": "Point",
                    "coordinates": [
                        36.8168874,
                        -1.3121609
                    ]
                },
                "distance": 2295.2209162366194
            }
        ],
        "timestamp": "2021-02-13T14:06:29.666431"
    }
    ```

    ***Distance*** pada hasil tersebut  dengan satuan Meter.

    