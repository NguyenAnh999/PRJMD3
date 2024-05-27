//  limit 8 
//   page 1 : 0-8 
//    page 2: 9- 18 
//    page 3: 18- 26

let thisPage = 1; 
let limit = 8; 
let list = document.querySelectorAll('.products .scrollbar-item'); 


function loadItem () {
    let beginGet = limit * (thisPage-1); 
    let endGet = limit * thisPage-1; 
    list.forEach((item,key) => {
        if(key>= beginGet && key <= endGet){
            item.style.display = 'block'; 
        } 
        else {
            item.style.display = 'none'
        }

    })
}
loadItem()

function listPage() {
    let count = Math.ceil(list.length/limit); 
    document.querySelector('.listPage').innerHTML='';

    if(thisPage !=1){
        let prev = document.createElement('li'); 
      
        prev.innerText = 'PREV'
        prev.setAttribute('onclick', "chagePage("+(thisPage-1)+")"); 
        document.querySelector('.listPage').appendChild(prev);

    }



    for (let i = 0; i <= count; i++) {
       let newPage = document.createElement('li');
       newPage.innerText = i; 
       if(i==thisPage) {
        newPage.classList.add('active'); 
       }
       newPage.setAttribute('onclick',"chagePage("+i+")" ); 
       document.querySelector('.listPage').appendChild(newPage);
        
    }
    if(thisPage != count) {
        let next = document.createElement('li'); 
        next.innerText='NEXT'; 

    }
}

function chagePage(i) {
    thisPage = i; 
    loadItem();
}

