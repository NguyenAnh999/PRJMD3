let checkUser = JSON.parse(localStorage.getItem("curenUser"))||false;
if (checkUser!=false) {
  window.location.href="../HTML/userinfo.html";
}else{
  let popup = document.getElementById("popup")
  const arrAdmin = JSON.parse(localStorage.getItem("user-data")); 
  function login (){
  
      event.preventDefault(); 
     let adminTk = document.getElementById("username").value; 
     let adminMk = document.getElementById("password").value; 
  
     for (let i = 0; i < arrAdmin.length; i++) {
       if (arrAdmin[i].name == adminTk && arrAdmin[i].password == adminMk) {
        if (arrAdmin[i].trangthai=="lock") {
          popup.classList.add("open-popup");
          document.getElementById('mksai').innerHTML='tk bi khoa roi cu'
          document.getElementById('lienhe').innerHTML='lien he voi chung roi'
     
          return;
        }else{
          localStorage.setItem("curenUser",JSON.stringify(arrAdmin[i]));
          window.location.href="../HTML/userinfo.html"
          return;
        }
       }
     }
     popup.classList.add("open-popup")
  }
  
  function closePopup() {
      popup.classList.remove("open-popup"); 
  }
  
}
// Đây là một admin cho sẵn để đăng nhập 
// Nếu không xoá đi thì khi làm CRUD sẽ bị lỗi ạ.
// Nó bị Reset lại giá trị ban đầu.


