document.addEventListener("DOMContentLoaded", function() {
    // Ambil semua tombol kategori
    const categoryButtons = document.querySelectorAll('.btn-secondary');
  
    // Tambahkan event listener untuk setiap tombol kategori
    categoryButtons.forEach(button => {
      button.addEventListener('click', () => {
        const category = button.dataset.category; // Ambil kategori dari data-category
  
        // Ambil semua item menu
        const menuItems = document.querySelectorAll('.grid-list > li');
  
        // Iterasi melalui setiap item menu
        menuItems.forEach(item => {
          const itemCategory = item.dataset.category; // Ambil kategori item menu
  
          // Periksa apakah item memiliki kategori yang sesuai dengan yang dipilih atau jika kategori adalah 'all'
          if (itemCategory === category || category === 'all') {
            item.style.display = 'block'; // Tampilkan item
          } else {
            item.style.display = 'none'; // Sembunyikan item
          }
        });
      });
    });
  });
  