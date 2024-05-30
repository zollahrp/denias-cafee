import base64

def convert_image_to_base64(image_path):
    with open(image_path, "rb") as image_file:
        image_base64 = base64.b64encode(image_file.read()).decode('utf-8')
    return image_base64

# Path relatif ke gambar espreso.png
image_path = "C:/Users/Zolla/Documents/Visual Studi Code/PROJEK DANIS COFE/denias-cafe/demo/src/main/resources/static/assets/img/espreso.png"

# Mengonversi gambar menjadi base64
image_base64 = convert_image_to_base64(image_path)
print("#"*50)
print("#"*50)
print(image_base64)
print("#"*50)