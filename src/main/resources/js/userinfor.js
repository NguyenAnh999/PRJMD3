function renderuserinfor() {
    const crUser=JSON.parse(localStorage.getItem('curenUser'))
    document.getElementById("anhiu").innerHTML=`
    <div class="profile-page">
      <div class="content">
        <div class="content-cover">
          <div class="content-avatar">
        
          </div>
          <div class="content-bull"><span></span><span></span><span></span><span></span><span></span>
          </div>
        </div>
        <div class="content-actions">
        
        <a href="../HTML/giohang.html">
          <i class='bx bx-cart'></i>
            <span>Giỏ hàng</span></a> 


            <a onclick="dangxuat()" ><i class='bx bx-log-out'></i>
           <span>Đăng xuất</span></a></div>



      <div class="contents">
        <div class="content-title">
          <h1 id="name">${crUser.fullname}</h1><span id="adress">${crUser.address}</span>
        </div>
        <div class="content-description">
          <p id="cart">Chào mừng bạn đến với MAROSA'S Quyn</p>
          <p>${crUser.email}</p>
        </div>
        <div class="content-button"><a class="button" href="./giohang.html">
            <div class="button-border"></div>
            <div class="button-bg"></div>
            <p class="button-text">Tiếp tục mua sắm</p></a></div>
      </div>
    </div>
      <div class="bg">
        <div><span></span><span></span><span></span><span></span><span></span><span></span><span></span>
        </div>
      </div>
    </div>
    <footer >
      <p class="newsletter-text" style="margin-top: 50px;">
        Xin chân thành cảm ơn quý khách đã luôn đồng hành cùng Marosa's Qynh - Chúc quý khách shopping vui vẻ
      </p>
      <p class="copyright" style="margin: 50px 0 0 10px;">
          &copy; 2024 code with love by QuynhAinXynh
      </p>
       <a href="#" class="logo">
          <img src="../logo/z5183538530354_f739e2fd01cb8c6a551e37fd2981b4f9.png" width="250" height="100" loading="lazy" alt="Glowing">
      </a>
  </footer>
    `
}
renderuserinfor()
function dangxuat() {
    localStorage.removeItem('curenUser');
    window.location.href="../HTML/login.html"
}