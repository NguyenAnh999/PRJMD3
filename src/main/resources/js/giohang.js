let xxx = JSON.parse(localStorage.getItem("curenUser"))||[];
// if (xxx.length==0) {
//   window.location.href="../HTML/login.html"
// }
const openCart = document.querySelector(".shopping");
const body = document.querySelector("body");
const closeCart = document.querySelector(".closeShopping");
// List product
const lists = document.querySelector(".list");

// List Cart
const cart = document.querySelector(".listCard");
const total = document.querySelector(".total");
const quantityCart = document.querySelector(".quantity");

openCart.addEventListener("click", () => {
  body.classList.add("active");
});

closeCart.addEventListener("click", () => {
  body.classList.remove("active");
});

// Backup data

const listProduct = JSON.parse(localStorage.getItem("data-product")) || [];

const listCart = JSON.parse(localStorage.getItem("curenUser"));
 
const lisUsers = JSON.parse(localStorage.getItem("user-data"));

function renderData() {
  for (let i = 0; i < listProduct.length; i++) {
if (listProduct[i].sale>0) {
  let value = listProduct[i];
  let newDiv = document.createElement("div");
  newDiv.classList.add("item");
  newDiv.innerHTML += `
      <div class="shop-card">

      <div class="card-banner img-holder" style="--width: 540; --height: 720;">
        <img src="${value.anh}" width="540" height="720" loading="lazy"
          alt="Pandora" class="img-cover">

  

        <div class="card-actions">

          <button class="action-btn" aria-label="add to cart" onclick="addToCart(${i})">
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
          <del class="del">${value.gia}Đ</del>

          <span class="span">${  value.gia/100*(100-value.sale)
        }</span>
        </div>

        <h3>
          <a href="#" class="card-title">${value.tensanpham}</a>
        </h3>

        <div class="card-rating">

          <div class="rating-wrapper" aria-label="5 start rating">
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
          </div>

          <p class="rating-text">1700 reviews</p>

        </div>

      </div>

    </div>
  `
  lists.appendChild(newDiv);
}else{
  let value = listProduct[i];
  let newDiv = document.createElement("div");
  newDiv.classList.add("item");
  newDiv.innerHTML += `
      <div class="shop-card">

      <div class="card-banner img-holder" style="--width: 540; --height: 720;">
        <img src="${value.anh}" width="540" height="720" loading="lazy"
          alt="Pandora" class="img-cover">

  

        <div class="card-actions">

          <button class="action-btn" aria-label="add to cart" onclick="addToCart(${i})">
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
          <del class="del">1.200.000Đ</del>

          <span class="span">${value.gia}</span>
        </div>

        <h3>
          <a href="#" class="card-title">${value.tensanpham}</a>
        </h3>

        <div class="card-rating">

          <div class="rating-wrapper" aria-label="5 start rating">
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
            <ion-icon name="star" aria-hidden="true"></ion-icon>
          </div>

          <p class="rating-text">1700 reviews</p>

        </div>

      </div>

    </div>
  `

  lists.appendChild(newDiv);
}

  }
  
}
renderData();

// [null, null, null, {}]
// ... spread operater
function addToCart(index) {
  //   console.log(index);
  // if (listCart.cart[index] == null) {
  //   listCart.cart[index] = { ...listProduct[index], quantity: 1 };
  // } else {
  //   listCart.cart[index].quantity += 1;
  // }
  let flag = false;
 
  for (let i = 0; i < listCart.cart.length; i++) {
    if (listCart.cart[i].masanpham==listProduct[index].masanpham) {
      listCart.cart[i].quantity +=1;
      flag=true;
    }
  }
  if (!flag) {
    listProduct[index].quantity=1;
    listCart.cart.push(listProduct[index]);
    
  }
  // if(listCart.cart.length==0){
  //   listCart.cart.push(listProduct[index]);
  // }

  localStorage.setItem("curenUser", JSON.stringify(listCart))||[];
  renderCart();
}
let moneyTotal =0;
function renderCart() {
  cart.innerHTML = "";
  let totalCount = 0;
  let totalCart = 0;
  let saletotal = 0;

  for (let i = 0; i < listCart.cart.length; i++) {
    const valueCart = listCart.cart[i];
    if (valueCart.sale>0) {
      let newLi = document.createElement("li");
      newLi.innerHTML = `
        <img src="${valueCart.anh}"/>
        <div class="text-cart">${valueCart.tensanpham}</div>
        <div class="text-cart">${ valueCart.gia/100*(100-valueCart.sale)}</div>
        <div><button onclick="quantityProduct(${i}, ${
        valueCart.quantity - 1
      })">-</button></div>
        <div class="count">${valueCart.quantity}</div>
        <div><button onclick="quantityProduct(${i}, ${
        valueCart.quantity + 1
      })">+</button></div>
    `
    totalCount += ( valueCart.gia/100*(100-valueCart.sale)) * valueCart.quantity;
    cart.appendChild(newLi);
    } else {
      let newLi = document.createElement("li");
      newLi.innerHTML = `
        <img src="${valueCart.anh}"/>
        <div class="text-cart">${valueCart.tensanpham}</div>
        <div class="text-cart">${valueCart.gia}</div>
        <div><button onclick="quantityProduct(${i}, ${
        valueCart.quantity - 1
      })">-</button></div>
        <div class="count">${valueCart.quantity}</div>
        <div><button onclick="quantityProduct(${i}, ${
        valueCart.quantity + 1
      })">+</button></div>
    `
    cart.appendChild(newLi);
    totalCount += valueCart.gia * valueCart.quantity;
    }
      ;
    
      saletotal += valueCart.gia * valueCart.quantity;
      totalCart++;
  }
  total.innerHTML =`thanh toán:  ${totalCount} Đ<br>tiết kiệm: ${saletotal-totalCount}`;
  quantityCart.textContent = totalCart;
  moneyTotal =totalCount;
}
renderCart();

function quantityProduct(index, newQuantity) {
  if (newQuantity <= 0) {
     listCart.cart.splice(index,1);
  } else {
    listCart.cart[index].quantity = newQuantity;
  }
  localStorage.setItem("curenUser", JSON.stringify(listCart));

for (let i = 0; i < lisUsers.length; i++) {
 if (listCart.id==lisUsers[i].id) {
  lisUsers[i]=listCart;
  localStorage.setItem("user-data", JSON.stringify(lisUsers));
 }
}

  renderCart();
}

let thisPage = 1;
let limit = 6;
let list = document.querySelectorAll('.list .item ');
function loadItem(){
    let beginGet = limit * (thisPage - 1);
    let endGet = limit * thisPage - 1;
    list.forEach((item, key)=>{
        if(key >= beginGet && key <= endGet){
            item.style.display = 'block';
        }else{
            item.style.display = 'none';
        }
    })
    listPage();
}
loadItem();
function listPage(){
    let count = Math.ceil(list.length / limit);
    document.querySelector('.listPage').innerHTML = '';

    if(thisPage != 1){
        let prev = document.createElement('li');
        prev.innerText = 'PREV';
        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
        document.querySelector('.listPage').appendChild(prev);
    }

    for(i = 1; i <= count; i++){
        let newPage = document.createElement('li');
        newPage.innerText = i;
        if(i == thisPage){
            newPage.classList.add('active');
        }
        newPage.setAttribute('onclick', "changePage(" + i + ")");
        document.querySelector('.listPage').appendChild(newPage);
    }

    if(thisPage != count){
        let next = document.createElement('li');
        next.innerText = 'NEXT';
        next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
        document.querySelector('.listPage').appendChild(next);
    }
}
function changePage(i){
    thisPage = i;
    loadItem();
}

function thanhtoan() {
  const arrOders = JSON.parse(localStorage.getItem("oders-data"))||[];
  let d = new Date();
  let year = d.getFullYear();
   let month = d.getMonth() + 1;
  let day = d.getDate();
  let oder={
    id:Date.now(),
    userIf:listCart,
    tongtien:moneyTotal,
    date :  day + '/' + month+ '/'+ year , 
    allcart:listCart.cart,
  }
  oder.userIf.cart=
  arrOders.push(oder);
  localStorage.setItem("oders-data",JSON.stringify(arrOders))
 
  listCart.cart=[];
  
  localStorage.setItem("curenUser",JSON.stringify(listCart))
  renderCart()
}
