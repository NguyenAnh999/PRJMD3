


// Thêm sự kiện cho các element

// element.addEventListener(event, function, useCapture);
//- `element`: đối tượng HTML mà sự kiện sẽ được đăng ký xử lý.
//- `event`: tên của sự kiện cần xử lý, ví dụ như "click", "mouseover", "keydown"...
//- `function`: hàm xử lý sự kiện, được gọi khi sự kiện xảy ra trên đối tượng.
//- `useCapture`: một giá trị boolean tùy chọn, xác định cách xử lý sự kiện. Nếu giá trị này là true, sự kiện sẽ được xử lý bằng phương thức capture,
// và nếu là false, sự kiện sẽ được xử lý bằng phương thức bubbling

const addEventOnElem = function (elem, type, callback) {
  if (elem.length > 1) {
    for (let i = 0; i < elem.length; i++) {
      elem[i].addEventListener(type, callback);
    }
  } else {
    elem.addEventListener(type, callback);
  }
}



// Thanh navbar

const navTogglers = document.querySelectorAll("[data-nav-toggler]");
const navbar = document.querySelector("[data-navbar]");
const navbarLinks = document.querySelectorAll("[data-nav-link]");
// const overlay = document.querySelector("[data-overlay]");

const toggleNavbar = function () {
  navbar.classList.toggle("active");
  overlay.classList.toggle("active");
}

addEventOnElem(navTogglers, "click", toggleNavbar);

const closeNavbar = function () {
  navbar.classList.remove("active");
  overlay.classList.remove("active");
}

addEventOnElem(navbarLinks, "click", closeNavbar);

 //Header sticky 

const header = document.querySelector("[data-header]");
const backTopBtn = document.querySelector("[data-back-top-btn]");

const headerActive = function () {
  if (window.scrollY > 150) {
    header.classList.add("active");
    backTopBtn.classList.add("active");
  } else {
    header.classList.remove("active");
    backTopBtn.classList.remove("active");
  }
}

addEventOnElem(window, "scroll", headerActive);

let flag = 0;

const headerSticky = function () {
  if (flag >= window.scrollY) {
    header.classList.remove("header-hide");
  } else {
    header.classList.add("header-hide");
  }

  flag = window.scrollY;
}

addEventOnElem(window, "scroll", headerSticky);


// Khi cuộn 

const sections = document.querySelectorAll("[data-section]");

const scrollReveal = function () {
  for (let i = 0; i < sections.length; i++) {

    //getBoundingClientRect trả về một đối tượng có một số cặp khóa / giá trị cung cấp cho 
    //bạn thông tin về kích thước và vị trí của phần tử trong trang web.
    // Ở đây mình lấy giá trị top trong DK 
    // Nếu tất cả 
    if (sections[i].getBoundingClientRect().top < window.innerHeight / 2) {
      sections[i].classList.add("active");
    }
  }
}

scrollReveal();

addEventOnElem(window, "scroll", scrollReveal);
function ramdom() {
  let arrProduct=JSON.parse(localStorage.getItem("data-product"))||[]; 
 return arrProduct[Math.floor(Math.random()*(arrProduct.length-1))]
}
function renderIteam() {
  
  let arrProduct=JSON.parse(localStorage.getItem("data-product"))||[]; 
  // for (let i = 0; i < arrProduct.length - 1; i++) {
  //   for (let j = i + 1; j < arrProduct.length; j++) {
  //     if (arrProduct[j].gia < arrProduct[i].gia) {
  //       let t = arrProduct[i];
  //       arrProduct[i] = arrProduct[j];
  //       arrProduct[j] = t;
  //     }
  //   }
  // }

  // console.log(arrProduct);
  let saleProduct=[];
  let sale2Product=[];
  let duoi600=[];
  for (let i = 0; i < arrProduct.length; i++) {
    if (arrProduct[i].saleQuatity>5) {
      saleProduct.push(arrProduct[i])
    }
    if (arrProduct[i].sale>0) {
      sale2Product.push(arrProduct[i])
    }
    if (arrProduct[i].gia<600000) {
      duoi600.push(arrProduct[i])
    }
     
  }
  let sp1=ramdom();
  let sp2=ramdom();
  let sp3=ramdom();
//  document.getElementById("home").innerHTML=`
//   <div class="container">
//
//   <ul class="has-scrollbar">
//
//     <li class="scrollbar-item">
//       <div class="hero-card has-bg-image" style="background-image: url(${sp1.anh});
//       background-position: left;">
//
//         <div class="card-content">
//
//           <h1 class="h1 hero-title">
//             Bộ Sưu Tập  <br>
//             ${sp1.loai}
//           </h1>
//
//           <p class="hero-text">
//             ${sp1.ghichu}
//           </p>
//
//           <p class="price">${sp1.gia}</p>
//
//           <a href="#" class="btn btn-primary">Shop Now</a>
//
//         </div>
//
//       </div>
//     </li>
//
//     <li class="scrollbar-item">
//       <div class="hero-card has-bg-image" style="background-image: url(${sp2.anh}">
//
//         <div class="card-content">
//
//           <h1 class="h1 hero-title">
//             Bộ Sưu Tập <br>
//             ${sp2.loai}
//           </h1>
//
//           <p class="hero-text">
//           ${sp2.ghichu}
//           </p>
//
//           <p class="price">${sp2.gia}</p>
//
//           <a href="#" class="btn btn-primary">Shop Now</a>
//
//         </div>
//
//       </div>
//     </li>
//
//     <li class="scrollbar-item">
//       <div class="hero-card has-bg-image" style="background-image: url(${sp3.anh})">
//
//         <div class="card-content">
//
//           <h1 class="h1 hero-title">
//             Bộ Sưu Tập <br>
//             ${sp3.loai}
//           </h1>
//
//           <p class="hero-text">
//           ${sp3.ghichu}
//           </p>
//
//           <p class="price">${sp3.gia}</p>
//
//           <a href="#" class="btn btn-primary">Shop Now</a>
//
//         </div>
//
//       </div>
//     </li>
//
//   </ul>
//
// </div>
//   `
//   document.getElementById("collection").innerHTML=`
//   <div class="container">
//
//       <ul class="collection-list">
//
//         <li>
//           <div class="collection-card has-before hover:shine">
//
//             <h2 class="h2 card-title">Giảm giá tới ${sale2Product[sale2Product.length-1].sale} %</h2>
//
//             <p class="card-text">Giảm từ ${sale2Product[sale2Product.length-1].gia} đến ${
//               sale2Product[sale2Product.length-1].gia/100*(100-sale2Product[sale2Product.length-1].sale)
//
//
//             }</p>
//
//             <a href="#" class="btn-link">
//               <span class="span">Shop Now</span>
//
//               <ion-icon name="arrow-forward" aria-hidden="true"></ion-icon>
//             </a>
//
//             <div class="has-bg-image" style="background-image: url( ${sale2Product[sale2Product.length-1].anh})"></div>
//
//           </div>
//         </li>
//
//         <li>
//           <div class="collection-card has-before hover:shine">
//
//             <h2 class="h2 card-title">Có gì mới ?</h2>
//
//             <p class="card-text">${arrProduct[arrProduct.length-1].loai}</p>
//
//             <a href="#" class="btn-link">
//               <span class="span">Khám phá ngay</span>
//
//               <ion-icon name="arrow-forward" aria-hidden="true"></ion-icon>
//             </a>
//
//             <div class="has-bg-image" style="background-image: url(${arrProduct[arrProduct.length-1].anh})"></div>
//
//           </div>
//         </li>
//
//         <li>
//           <div class="collection-card has-before hover:shine">
//
//             <h2 class="h2 card-title">Sản phẩm mới khác</h2>
//
//             <p class="card-text">Số sản phẩm đã bán: ${arrProduct[arrProduct.length-1].saleQuatity}</p>
//
//             <a href="#" class="btn-link">
//               <span class="span">Khám phá ngay</span>
//
//               <ion-icon name="arrow-forward" aria-hidden="true"></ion-icon>
//             </a>
//
//             <div class="has-bg-image" style="background-image: url(${arrProduct[arrProduct.length-2].anh})"></div>
//
//           </div>
//         </li>
//
//       </ul>
//
//     </div>
//   `
if (saleProduct.length!=0) {
  for (let i = 0; i < saleProduct.length; i++) {
    document.getElementById("has-scrollbar").innerHTML+=`
    <li class="scrollbar-item">
    <div class="shop-card">

      <div class="card-banner img-holder" style="--width: 540; --height: 720;">
        <img src="${saleProduct[i].anh}" width="540" height="720" loading="lazy"
          alt="Pandora" class="img-cover">

        <span class="badge" aria-label="20% off">-${saleProduct[i].sale}%</span>

        <div class="card-actions">

          <button class="action-btn" aria-label="add to cart">
            <ion-icon name="bag-handle-outline" aria-hidden="true"></ion-icon>
          </button>

          <button class="action-btn" aria-label="add to whishlist">
            <ion-icon name="star-outline" aria-hidden="true"></ion-icon>
          </button>

          <button class="action-btn" aria-label="compare">
            <ion-icon name="repeat-outline" aria-hidden="true"></ion-icon>
          </button>

        </div>
      </div>

      <div class="card-content">

        <div class="price">
          <del class="del">${saleProduct[i].gia}Đ</del>

          <span class="span">${
            saleProduct[i].gia/100*(100-saleProduct[i].sale)

             
          }Đ</span>
        </div>

        <h3>
          <a href="#" class="card-title">Facial Design</a>
        </h3>

        <div class="card-rating">

          <div class="rating-wrapper" aria-label="5 start rating">
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
          </div>

          <p class="rating-text">${saleProduct[i].saleQuatity} lượt mua</p>

        </div>

      </div>

    </div>
  </li>
    `
  }
}
for (let i = 0; i < duoi600.length; i++) {
if (duoi600[i].sale>0) {
  document.getElementById("has-scrollbar2").innerHTML+=`
  
  <li class="scrollbar-item">
  <div class="shop-card">

    <div class="card-banner img-holder" style="--width: 540; --height: 720;">
      <img src="${duoi600[i].anh}" width="540" height="720" loading="lazy"
        alt="Pandora" class="img-cover">

      <span class="badge" aria-label="20% off">-${duoi600[i].sale}%</span>

      <div class="card-actions">

        <button class="action-btn" aria-label="add to cart">
          <ion-icon name="bag-handle-outline" aria-hidden="true"></ion-icon>
        </button>

        <button class="action-btn" aria-label="add to whishlist">
          <ion-icon name="star-outline" aria-hidden="true"></ion-icon>
        </button>

        <button class="action-btn" aria-label="compare">
          <ion-icon name="repeat-outline" aria-hidden="true"></ion-icon>
        </button>

      </div>
    </div>

    <div class="card-content">

      <div class="price">
        <del class="del">${duoi600[i].gia}Đ</del>

        <span class="span">${
          duoi600[i].gia/100*(100-duoi600[i].sale)       
        }</span>
      </div>

      <h3>
        <a href="#" class="card-title">Pandora</a>
      </h3>

      <div class="card-rating">

        <div class="rating-wrapper" aria-label="5 start rating">
          <ion-icon name="star" aria-hidden="true"></ion-icon>
          <ion-icon name="star" aria-hidden="true"></ion-icon>
          <ion-icon name="star" aria-hidden="true"></ion-icon>
          <ion-icon name="star" aria-hidden="true"></ion-icon>
          <ion-icon name="star" aria-hidden="true"></ion-icon>
        </div>

        <p class="rating-text">${duoi600[i].saleQuatity} lượt mua</p>

      </div>

    </div>

  </div>
</li>
  `
}else{
  document.getElementById("has-scrollbar2").innerHTML+=`
  
  <li class="scrollbar-item">
  <div class="shop-card">

    <div class="card-banner img-holder" style="--width: 540; --height: 720;">
      <img src="${duoi600[i].anh}" width="540" height="720" loading="lazy"
        alt="Pandora" class="img-cover">

      

      <div class="card-actions">

        <button class="action-btn" aria-label="add to cart">
          <ion-icon name="bag-handle-outline" aria-hidden="true"></ion-icon>
        </button>

        <button class="action-btn" aria-label="add to whishlist">
          <ion-icon name="star-outline" aria-hidden="true"></ion-icon>
        </button>

        <button class="action-btn" aria-label="compare">
          <ion-icon name="repeat-outline" aria-hidden="true"></ion-icon>
        </button>

      </div>
    </div>

    <div class="card-content">

      <div class="price">

        <span class="span">${
          duoi600[i].gia     
        }</span>
      </div>

      <h3>
        <a href="#" class="card-title">Pandora</a>
      </h3>

      <div class="card-rating">

        <div class="rating-wrapper" aria-label="5 start rating">
          <ion-icon name="star" aria-hidden="true"></ion-icon>
          <ion-icon name="star" aria-hidden="true"></ion-icon>
          <ion-icon name="star" aria-hidden="true"></ion-icon>
          <ion-icon name="star" aria-hidden="true"></ion-icon>
          <ion-icon name="star" aria-hidden="true"></ion-icon>
        </div>

        <p class="rating-text">${duoi600[i].saleQuatity} lượt mua</p>

      </div>

    </div>
  </div>
</li>
  `
}
  
}
}
renderIteam()