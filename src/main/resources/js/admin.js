//Hiệu ứng hover
let xxx=localStorage.getItem("login")||"";
// if (xxx=="") {
//     window.location.href="../HTML/Adminlogin.html"
//
// }
let lists;
let list = document.querySelectorAll(".navigation li");

function activeLink() {
  list.forEach((item) => { 
    item.classList.remove("hovered");
  });
  this.classList.add("hovered");
}

list.forEach((item) => item.addEventListener("mouseover", activeLink));

// Bật tắt menu
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
  navigation.classList.toggle("active");
  main.classList.toggle("active");
};

// //Themsp 
// //Lấy dữ liệu ở trong ô input ra
// const nameProduct = document.getElementById("nameProduct");
// const price= document.getElementById("price");
// const numberProduct= document.getElementById("numberProduct");
// const model= document.getElementById("model");
// const number= document.getElementById("number");
// const dateProduct= document.getElementById("dateProduct");
// const imageProduct= document.getElementById("imageProduct");
// const mieutasp= document.getElementById("mieuta");

let exit = -1; 
let eitting = false; 

// function renderProduct() {
//   const listProduct = JSON.parse(localStorage.getItem("data-product")); 
//   let newProduct = ""; 

//   for (let i = 0; i < listProduct.length; i++) {
//     newProduct += `
//     <tr class="itemsp">
//     <td > ${i +1} </td>
//     <td>${listProduct[i].tensanpham}</td>
//     <td>${listProduct[i].gia}</td>
//     <td>${listProduct[i].masanpham}</td>
//     <td>${listProduct[i].soluong}</td>
//     <td>${listProduct[i].date}</td>
//     <td>${listProduct[i].ghichu}</td>
//     <td><button onclick="editProduct(${i})">Edit</button><button onclick="deleteProduct(${i})">Xoa</button></td>
//     <td>
//     </td>
// </tr>
//      `;

//   }
// tableProduct.innerHTML=newProduct ; 
// }

// // function moThemsanpham() {
// //   document.getElementById("themSp add").innerHTML=``; 
// //   document.getElementById("themSp add").innerHTML=`
// //   <label style="font-size: 20px; margin: 5px;
// //             cursor: pointer;"><i class="fa-solid fa-less-than"></i> Back</label>
// //                 <form id="formAddproduct">
// //                     <h1>Thêm sản phẩm</h1>
// //                     <label>Tên sản phẩm : </label><br>
// //                     <input type="text" id="nameProduct">
// //                     <br>

// //                     <label>Giá :</label><br>
// //                     <input type="number"  id="price">
// //                     <br>

// //                     <label>Mã sản phẩm : </label><br>
// //                     <input type="text"  id="numberProduct">
// //                     <br>
// //                     <label>Hãng: </label><br>
// //                     <input type="text" id="model">
// //                     <br>

// //                     <label>Số lượng: </label><br>
// //                     <input type="number"  id="number">
// //                     <br>

// //                     <label>Ngày tháng:</label><br>
// //                     <input type="datetime"  id="dateProduct">
// //                     <br>
// //                     <label> Ảnh :
// //                     </label>
// //                     <br>
// //                     <input type="text"  id="imageProduct">
// //                     <br>
// //                     <button>Choose image</button> <br>

// //                     <label for="">Miêu tả:</label>
// //                     <br>

// //                     <textarea style="resize: none;" cols="89" rows="5" placeholder="Nhập miêu tả " id="mieuta"></textarea>
// //                     <br>

// //                     <button type="submit">Thêm sản phẩm </button>
// //                 </form>
  
// //   `; 


// // }
// //Xoá sản phẩm
// function deleteProduct(index) {

// const getDatalocal = JSON.parse(localStorage.getItem("data-product")); 

// getDatalocal.splice(index,1); 
// // cập nhật lại trên lc
// localStorage.setItem("data-product",JSON.stringify(getDatalocal)); 
// renderProduct(); 
// }
// // Phantrang : Mỗi trang có 5 sản phẩm, 
// // page1 : 0-4 
// // page2 : 5-9
// // page3 : 10-14
// // 
// let thisPage = 1;
// let limit = 5;
// let list = document.querySelectorAll('#tableProduct .itemsp ');

// function loadItem(){
//     let beginGet = limit * (thisPage - 1);
//     let endGet = limit * thisPage - 1;
//     list.forEach((itemsp, key)=>{
//       if (key >= beginGet && key <= endGet) {
//         itemsp.style.display = 'table-row';
//     } else {
//         itemsp.style.display = 'none';
//     }
//     })
//     listPage();
// }

// function listPage(){
//     let count = Math.ceil(list.length / limit);
//     document.querySelector('.listPage').innerHTML = '';

//     if(thisPage != 1){
//         let prev = document.createElement('li');
//         prev.innerText = 'PREV';
//         prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
//         document.querySelector('.listPage').appendChild(prev);
//     }

//     for(let i = 1; i <= count; i++){
//         let newPage = document.createElement('li');
//         newPage.innerText = i;
//         if(i == thisPage){
//             newPage.classList.add('active');
//         }
//         newPage.setAttribute('onclick', "changePage(" + i + ")");
//         document.querySelector('.listPage').appendChild(newPage);
//     }

//     if(thisPage != count){
//         let next = document.createElement('li');
//         next.innerText = 'NEXT';
//         next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
//         document.querySelector('.listPage').appendChild(next);
//     }
// }
// function changePage(i){
//     thisPage = i;
//     loadItem();
// }
function editProduct(index) {
    hienAdd()
  const getDatalocal = JSON.parse(localStorage.getItem("data-product")); 

  nameProduct.value = getDatalocal[index].tensanpham; 
  price.value = getDatalocal[index].gia;  
  model.value = getDatalocal[index].hang; 
  number.value = getDatalocal[index].soluong; 
  imageProduct.value = getDatalocal[index].anh; 
  mieutasp.value = getDatalocal[index].ghichu; 

  exit= index ; 
  eitting = true; 
  document.getElementById("btn-submit").innerText="update";
  console.log(exit);
  console.log(eitting);
}
function renderSp () {
    let userarr =JSON.parse(localStorage.getItem("user-data"));
    let overoder = JSON.parse(localStorage.getItem("over-data"))||[];
    if (overoder.length==0) {
        return
    }
    let tongtienthudc=0;
    for (let i = 0; i < overoder.length; i++) {
        tongtienthudc+=overoder[i].tongtien
        
    }
    let oder = JSON.parse(localStorage.getItem("oders-data"));
    let spbr = JSON.parse(localStorage.getItem("data-product"));
    let tongslbr=0;
    for (let i = 0; i < spbr.length; i++) {
        tongslbr+=spbr[i].saleQuatity;
        
    }
document.getElementsByClassName("chitietOrder")[0].innerHTML= ``
document.getElementsByClassName("dashboardSession")[0].innerHTML= ``; 
document.getElementsByClassName("dashboardSession")[0].innerHTML = `
 
  <div class="cardBox">
      <div class="card">
          <div class="iconBx">
              <i class="fa-solid fa-bag-shopping"></i>
          </div>
          <div>
              <div class="numbers">${tongtienthudc}Đ</div>
              <div class="cardName">Tổng doanh thu</div>
          </div>


      </div>

      <div class="card">
          <div class="iconBx">
              <i class="fa-regular fa-user"></i>
          </div>
          <div>
              <div class="numbers">${userarr.length}</div>
              <div class="cardName">Lượng thành viên</div>
          </div>
      </div>

      <div class="card">
          <div class="iconBx">
              <i class="fa-solid fa-arrows-rotate"></i>
          </div>
          <div>
              <div class="numbers">${oder.length}</div>
              <div class="cardName">Oder</div>
          </div>


      </div>

      <div class="card">
          <div>
              <div class="numbers">${tongslbr}</div>
              <div class="cardName">tổng số lượng sản phẩm đã bán ra</div>
          </div>

          <div class="iconBx">
              <ion-icon name="cash-outline"></ion-icon>
          </div>
      </div>
  </div>
  <div class="welcomeImg">
      <img src="../profile/czNmcy1wcml2YXRlL3Jhd3BpeGVsX2ltYWdlcy93ZWJzaXRlX2NvbnRlbnQvbHIvam9iMTQ0LWJlZS0wNDBlLmpwZw.jpg"
          alt="">
  </div>
  `  
}
renderSp ()
// function themsp() {
// document.getElementsByClassName("dashboardSession")[0].innerHTML= ``
// document.getElementsByClassName("chitietOrder")[0].innerHTML += `
//   <div class="dsOder">
//   <div class="cardHeader" style="justify-content: space-between;">
//       <button onclick="hienAdd()" style="padding-left: 0;"> <i class="fa-solid fa-plus"></i>Thêm sản phẩm</button>
//       <a href="#" class="btn">Sắp xếp<i style="padding: 6px;" class="fa-solid fa-v"></i></a>
//   </div>
//   <table>
//       <thead>
//           <tr>
//               <th>STT</th>
//               <th>Tên Sản Phẩm </th>
//               <th>Giá</th>
//               <th> Mã sản phẩm</th>
//               <th>Số lượng</th>
//               <th>Ngày tháng</th>
//               <th>Ghi chú</th>
//               <th>Action</th>

//           </tr>
//       </thead>
//       <tbody id="tableProduct">
//       </tbody>
//   </table>

//   <ul class="listPage">
//   </ul>
// </div>



  
  
  
  
//   `
// }
// function hienAdd() {
    
//     document.getElementsByClassName("dashboardSession")[0].innerHTML= ``
//   document.getElementsByClassName("chitietOrder")[0].innerHTML= ``; 
//   document.getElementsByClassName("chitietOrder")[0].innerHTML += `
  
//   <div class="themSp add">
//   <!-- Form người dùng nhập -->
//    <label style="font-size: 20px; margin: 5px;
// cursor: pointer;"><i class="fa-solid fa-less-than"></i> Back</label>
//   <form id="formAddproduct">
//       <h1>Thêm sản phẩm</h1>
//       <label>Tên sản phẩm : </label><br>
//       <input type="text" id="nameProduct">
//       <br>

//       <label>Giá :</label><br>
//       <input type="number"  id="price">
//       <br>
//       <label>Hãng: </label><br>
//       <input type="text" id="model">
//       <br>

//       <label>Số lượng: </label><br>
//       <input type="number"  id="number">
//       <br>
//       <label> Ảnh :
//       </label>
//       <br>
//       <input type="text"  id="imageProduct">
//       <br>

//       <label for="">Miêu tả:</label>
//       <br>

//       <textarea style="resize: none;" cols="89" rows="5" placeholder="Nhập miêu tả " id="mieuta"></textarea>
//       <br>

//       <button type="submit" id="btn-submit" onclick="">Thêm sản phẩm </button>
//   </form> 
// </div>
// <div class="dsOder">
//   <div class="cardHeader" style="justify-content: space-between;">
//       <button onclick="hienAdd()" style="padding-left: 0;"> <i class="fa-solid fa-plus"></i>Thêm sản phẩm</button>
//       <a href="#" class="btn">Sắp xếp<i style="padding: 6px;" class="fa-solid fa-v"></i></a>
//   </div>
//   <table>
//       <thead>
//           <tr>
//               <th>STT</th>
//               <th>Tên Sản Phẩm </th>
//               <th>Giá</th>
//               <th> Mã sản phẩm</th>
//               <th>Số lượng</th>
//               <th>Ngày tháng</th>
//               <th>Ghi chú</th>
//               <th>Action</th>

//           </tr>
//       </thead>
//       <tbody id="tableProduct">
//       </tbody>
//   </table>

//   <ul class="listPage">
//   </ul>
// </div>`
// const nameProduct = document.getElementById("nameProduct");
// const price= document.getElementById("price");
// const numberProduct= document.getElementById("numberProduct");
// const model= document.getElementById("model");
// const number= document.getElementById("number");
// const dateProduct= document.getElementById("dateProduct");
// const imageProduct= document.getElementById("imageProduct");
// const mieutasp= document.getElementById("mieuta");

// const btnn= document.getElementById("btn-submit");

// let exit = -1; 
// let eitting = false; 
// const formAddproduct = document.getElementById("formAddproduct");  

// var tableProduct = document.getElementById("tableProduct");
// formAddproduct.addEventListener("submit",(e) => {
//     e.preventDefault();     
  
    
//     let d = new Date();
//   let year = d.getFullYear();
//   let month = d.getMonth() + 1;
//   let day = d.getDate();
//     const arrProduct = {
//       // stt: ,
//       tensanpham : nameProduct.value, 
//       gia :price.value , 
//       masanpham : Date.now(), 
//       hang :  model.value, 
//       soluong : number.value, 
//       date :  day + '/' + month+ '/'+ year , 
//       anh : imageProduct.value,
//       ghichu : mieutasp.value, 
//     }
//    const productLocal= JSON.parse(localStorage.getItem("data-product")) || [];
//    if(eitting) {
//     productLocal[exit]= arrProduct; 
//     exit = false; 
  
  
//     //update 
//    } else {
//     productLocal.push(arrProduct); 
//    }
  
//    localStorage.setItem("data-product",JSON.stringify(productLocal));
//    renderProduct(); 


//   }); 



// }
function hienOder(){
    document.getElementsByClassName("dashboardSession")[0].innerHTML= ``

  document.getElementsByClassName("chitietOrder")[0].innerHTML= ``; 
  document.getElementsByClassName("chitietOrder")[0].innerHTML += `
  
  
  <div class="ttdonhang">
</div>
<div class="dsOder">

  <div class="cardHeader">
      <a href="#" class="btn">Sắp xếp</a>
  </div>

  <table >
      <thead>
          <tr>
              <td>STT </td>
              <td>Tên người mua</td>
              <td>Mã đơn hàng </td>
              <td>Giá</td>
              <td>dia chi</td>
              <td>Ngày tháng</td>
              <td>Ghi chú</td>
              <td>Trạng thái</td>
              <td>Action</td>

          </tr>
      </thead>

      <tbody id="tableOder">
     

      </tbody>
  </table>

  <ul class="listPage">
  </ul>
</div>
  
  `
  let Oders = JSON.parse(localStorage.getItem("oders-data"))
  let a="";
  for (let i = 0; i < Oders.length; i++) {
    for (let index = 0; index < Oders[i].allcart.length; index++) {
      a +=`
      ${Oders[i].allcart[index].tensanpham}:${Oders[i].allcart[index].quantity}chiếc<br>
        `
        
    }
    document.getElementById("tableOder").innerHTML+=`
    <tr class="itemsp">
              <td> ${i+1}</td>
              <td>${Oders[i].userIf.fullname}</td>
              <td>${Oders[i].id} </td>
              <td>${Oders[i].tongtien}</td>
              <td>${Oders[i].userIf.address}</td>
              <td>${Oders[i].date}</td>
              <td>${a}</td>
              <td><span class="trangthai cxn">Chưa xác nhận </span></td>
              <td><button onclick="over(${i})">xac nhan</button></td>
          </tr>
    `
  }
  lists = document.querySelectorAll('#tableOder .itemsp ');
  loadItem()
} 
function over(id) {
    const getDatalocal = JSON.parse(localStorage.getItem("oders-data"))||[]; 
    const overOderData = JSON.parse(localStorage.getItem("over-data"))||[]; 
    const productData = JSON.parse(localStorage.getItem("data-product"))||[]; 
for (let i = 0; i < getDatalocal[id].allcart.length; i++) {
    for (let j = 0; j < productData.length; j++) {
        if (getDatalocal[id].allcart[i].masanpham==productData[j].masanpham) {
            console.log(productData[j].saleQuatity);
            console.log(getDatalocal[id].allcart[i].quantity);
            productData[j].saleQuatity+=getDatalocal[id].allcart[i].quantity


            localStorage.setItem("data-product",JSON.stringify(productData))
        }
    }
}
    overOderData.push(getDatalocal[id])
    localStorage.setItem("over-data",JSON.stringify(overOderData)); 
    getDatalocal.splice(id,1); 
    // cập nhật lại trên lc
    localStorage.setItem("oders-data",JSON.stringify(getDatalocal)); 

    hienOder()
}
// 8888888888888888888888888888888888888888888888888888888888888
function hienUser(){
   document.getElementsByClassName("dashboardSession")[0].innerHTML= ``
  document.getElementsByClassName("chitietOrder")[0].innerHTML= ``; 
  document.getElementsByClassName("chitietOrder")[0].innerHTML += 
  `
  <div class="dsOder">
  <div class="cardHeader" style="justify-content: space-between">
      <h2>Quản lý User</h2>
      <a href="#" class="btn">Sắp xếp</a>
  </div>
  <!-- <div class="search">
  <label>
      <input type="text" placeholder="Search here">
      <i  class="fa-solid fa-magnifying-glass"></i>
  </label>
</div> -->

  <table>
      <thead>
          <tr>
          <td>STT </td>
              <td>ID </td>
              <td>Tên Người Dùng </td>
              <td> Email</td>
              <td>Trang Thai</td>
              <td>Action</td>
          </tr>
      </thead>
      <tbody id="tableUser">


      </tbody>
  </table>
  <ul class="listPage">
  </ul>
</div>
  `
const usersArr = JSON.parse(localStorage.getItem('user-data'))||[];
for (let i = 0; i < usersArr.length; i++) {
    document.getElementById('tableUser').innerHTML += `
    <tr class="itemsp">
    <td > ${i +1} </td>
    <td>${usersArr[i].id}</td>
    <td>${usersArr[i].name}</td>
    <td>${usersArr[i].email}</td>
    <td>${usersArr[i].trangthai}</td>
    <td><button  onclick="actionUser(${usersArr[i].id})" >Action</button></td>
    <td>
    </td>
</tr>
     `;
     lists = document.querySelectorAll('#tableUser .itemsp ');
     loadItem()
}
}
function actionUser(id) {
    const usersArr = JSON.parse(localStorage.getItem('user-data'))||[];
    for (let i = 0; i < usersArr.length; i++) {
        if (id==usersArr[i].id) {
            if (usersArr[i].trangthai=="open") {
                usersArr[i].trangthai="lock"
            }else{
                usersArr[i].trangthai="open"
            }
        }
    }
    localStorage.setItem('user-data',JSON.stringify(usersArr))
    hienUser()
}

//88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888

function themsp() {
document.getElementsByClassName("dashboardSession")[0].innerHTML= ``
document.getElementsByClassName("chitietOrder")[0].innerHTML = `
  <div class="dsOder">
  <div class="cardHeader" style="justify-content: space-between;">
      <button onclick="hienAdd()" style="padding-left: 0;"> <i class="fa-solid fa-plus"></i>Thêm sản phẩm</button>
      <a href="#" class="btn">Sắp xếp<i style="padding: 6px;" class="fa-solid fa-v"></i></a>
  </div>
  <table>
      <thead>
          <tr>
              <th>STT</th>
              <th>Tên Sản Phẩm </th>
              <th>Giá</th>
              <th> Mã sản phẩm</th>
              <th>Số lượng</th>
              <th>Ngày tháng</th>
              <th>Ghi chú</th>
              <th>Action</th>

          </tr>
      </thead>
      <tbody id="tableProduct">
      </tbody>
  </table>

  <ul class="listPage">
  </ul>
</div>
  `

  
  renderProduct()
}
function hienAdd() {
    
    document.getElementsByClassName("dashboardSession")[0].innerHTML= ``
  document.getElementsByClassName("chitietOrder")[0].innerHTML= ``; 
  document.getElementsByClassName("chitietOrder")[0].innerHTML = `
  
  <div class="themSp add">
  <!-- Form người dùng nhập -->
   <label onclick="themsp()" style="font-size: 20px; margin: 5px;
cursor: pointer;"><i class="fa-solid fa-less-than"></i> Back</label>
  <div class="form" id="formAddproduct">
      <h1>Thêm sản phẩm</h1>
      <label>Tên sản phẩm : </label><br>
      <input type="text" id="nameProduct">
      <br>

      <label>Giá :</label><br>
      <input type="number"  id="price">
      <br>
      <label>Hãng: </label><br>
      <input type="text" id="model">
      <br>
      <label>loai: </label><br>
      <input type="text" id="loai">
      <br>

      <label>star: </label><br>
      <select id="star" name="dropdown">
<option value="5" selected>1 Sao</option>
<option value="4" selected>2 Sao</option>
<option value="3" selected>3 Sao</option>
<option value="2" selected>4 Sao</option>
<option value="1" selected>5 Sao</option>

</select>




      <br>
      <label>sale: </label><br>
      <input type="text" id="sale">
      <br>
      <label>Số lượng: </label><br>
      <input type="number"  id="number">
      <br>
      <label> Ảnh :
      </label>
      <br>
      <input type="text"  id="imageProduct">
      <br>

      <label for="">Miêu tả:</label>
      <br>

      <textarea style="resize: none;" cols="30" rows="5" placeholder="Nhập miêu tả " id="mieutasp"></textarea>
      <br>

      <button id="btn-submit" onclick="addProduct()">Thêm sản phẩm </button>
  </div> 
</div>
<div class="dsOder">
  <div class="cardHeader" style="justify-content: space-between;">
      <button onclick="hienAdd()" style="padding-left: 0;"> <i class="fa-solid fa-plus"></i>Thêm sản phẩm</button>
      <a href="#" class="btn">Sắp xếp<i style="padding: 6px;" class="fa-solid fa-v"></i></a>
  </div>
  <table>
      <thead>
          <tr>
              <th>STT</th>
              <th>Tên Sản Phẩm </th>
              <th>Giá</th>
              <th> Mã sản phẩm</th>
              <th>Số lượng</th>
              <th>Ngày tháng</th>
              <th>Ghi chú</th>
              <th>Action</th>
          </tr>
      </thead>
      <tbody id="tableProduct">
      </tbody>
  </table>

  <ul class="listPage">
  </ul>
</div>`
renderProduct()
  };
function addProduct() {
const loai = document.getElementById("loai");
const nameProduct = document.getElementById("nameProduct");
const price= document.getElementById("price");
const model= document.getElementById("model");
const number= document.getElementById("number");
const imageProduct= document.getElementById("imageProduct");
const mieutasp= document.getElementById("mieutasp");
const sale = document.getElementById("sale");
const star = document.getElementById("star");
   let d = new Date();
  let year = d.getFullYear();
  let month = d.getMonth() + 1;
  let day = d.getDate();
    const arrProduct = {
      // stt: ,
      tensanpham : nameProduct.value, 
      gia :price.value , 
      masanpham : Date.now(), 
      hang :  model.value, 
      soluong : number.value, 
      date :  day + '/' + month+ '/'+ year , 
      anh : imageProduct.value,
      ghichu : mieutasp.value,
      saleQuatity:0,
      sale:sale.value,
      loai:loai.value,
      star:star.value,
      quantity:0,
    }
   const productLocal= JSON.parse(localStorage.getItem("data-product")) || [];
      if(eitting==true) {
    productLocal[exit]= arrProduct; 
    localStorage.setItem("data-product",JSON.stringify(productLocal));
    eitting=false;
    exit=-1;
  
   } else {
    productLocal.push(arrProduct); 
    localStorage.setItem("data-product",JSON.stringify(productLocal));
   }
   document.getElementById("btn-submit").innerText="thêm sản phẩm";
   renderProduct()
}
function renderProduct() {
    tableProduct.innerHTML = ``;
  const listProduct = JSON.parse(localStorage.getItem("data-product"))|| []; 
  for (let i = 0; i < listProduct.length; i++) {
    tableProduct.innerHTML += `
    <tr class="itemsp">
    <td > ${i +1} </td>
    <td>${listProduct[i].tensanpham}</td>
    <td>${listProduct[i].gia}</td>
    <td>${listProduct[i].masanpham}</td>
    <td>${listProduct[i].soluong}</td>
    <td>${listProduct[i].date}</td>
    <td>${listProduct[i].ghichu}</td>
    <td><button onclick="editProduct(${i})">Edit</button><button onclick="deleteProduct(${i})">Xoa</button></td>
    <td>
    </td>
</tr>
     `;
     lists = document.querySelectorAll('#tableProduct .itemsp ');
     loadItem()
  }

}
function deleteProduct(index) {

    const getDatalocal = JSON.parse(localStorage.getItem("data-product")); 
    
    getDatalocal.splice(index,1); 
    // cập nhật lại trên lc
    localStorage.setItem("data-product",JSON.stringify(getDatalocal)); 

    renderProduct()
    }
    let thisPage = 1;
    let limit = 5;
    
function loadItem(){
    let beginGet = limit * (thisPage - 1);
    let endGet = limit * thisPage - 1;
    lists.forEach((itemsp, key)=>{
      if (key >= beginGet && key <= endGet) {
        itemsp.style.display = 'table-row';
    } else {
        itemsp.style.display = 'none';
    }
    })
    listPage();
}

function listPage(){
    let count = Math.ceil(lists.length / limit);
    document.querySelector('.listPage').innerHTML = '';

    if(thisPage != 1){
        let prev = document.createElement('li');
        prev.innerText = 'PREV';
        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
        document.querySelector('.listPage').appendChild(prev);
    }

    for(let i = 1; i <= count; i++){
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
