import psycopg2
from psycopg2 import sql
from PIL import Image
import io
import os

# Fungsi untuk mengonversi gambar ke byte array
def image_to_byte_array(image_path):
    with Image.open(image_path) as image:
        img_byte_arr = io.BytesIO()
        image.save(img_byte_arr, format=image.format)
        img_byte_arr = img_byte_arr.getvalue()
    return img_byte_arr

# Informasi koneksi database
conn = psycopg2.connect(
    dbname="Denias Cafe",
    user="postgres",
    password="aku1 ingin6 menjadi# Seorang Developer",
    host="localhost",
    port="29424"
)

# Path relatif ke gambar
current_directory = os.path.dirname(__file__)
gambar_menu_path = os.path.join(current_directory, 'espreso.png')

# Data yang ingin disimpan
category = "Coffee"
deskripsi_menu = "Espresso hot is a strong, concentrated coffee shot brewed using high pressure to extract intense flavors and rich aroma from finely ground coffee beans."
gambar_menu = image_to_byte_array(gambar_menu_path)
harga_menu = 13000.0
nama_menu = "Espresso Hot"
recommended = True

# Query SQL untuk menyimpan data ke database
sql_query = sql.SQL("INSERT INTO menu (category, deskripsi_menu, gambar_menu, harga_menu, nama_menu, recommended) VALUES (%s, %s, %s, %s, %s, %s)")

# Membuat cursor
cur = conn.cursor()

try:
    # Eksekusi query dengan parameter
    cur.execute(sql_query, (category, deskripsi_menu, psycopg2.Binary(gambar_menu), harga_menu, nama_menu, recommended))
    # Commit perubahan ke database
    conn.commit()
    print("Data berhasil disimpan ke database!")
except psycopg2.Error as e:
    print("Error:", e)
finally:
    # Menutup koneksi dan cursor
    cur.close()
    conn.close()