

let user;
let items;
let itemsContainer = document.getElementById("items-container");

window.addEventListener ("load", async () => {
     
  let response = await fetch("/api/session");
  
  let responseBody = await response.json();
  
  if(!responseBody.successful) {
    window.location = "../";
  }

  window.history.pushState(null, null, window.location.href);
  window.onpopstate = function () {
  window.history.go(1);
};

  user = responseBody.data;
  userName = responseBody.data

  items = await getAllItems();
  displayItems();

  let getUsername = localStorage.getItem("userName")

  let usernameElement = document.getElementById("dashboard");
  usernameElement.innerText = "Welcome " + getUsername;
  usernameElement.style.display ='block';
}) 

async function getAllItems() {
  let response = await fetch("/api/item");

  let responseBody =  await response.json();

  return responseBody.data;
}

let logoutBtn = document.getElementById("logout-btn");

logoutBtn.addEventListener("click", () => {

  fetch("/session", { method: "DELETE"});

   window.location = "../";
});

const displayItems = () => {
  
  items.forEach(item => {

    let itemElement = document.createElement("div");
    itemElement.className = "item";
    itemElement.id = `item-${item.id}`;

    itemElement.addEventListener("click", () => {
      console.log("event triggered");
    })

    let itemNameContainerElement = document.createElement("div");
    itemNameContainerElement.className = "item-name-container"

    let itemNameElement = document.createElement("div");
    itemNameElement.className = "item-name";
    itemNameElement.innerText = `${item.qty} ${item.name}`;
    console.log(item);
    
    itemNameElement.addEventListener("click", () => {
      console.log("event triggered");
    })

    if (item.inCart) {
      itemNameElement.style.textDecoration = "line-through";
    }

    itemNameElement.addEventListener("click", async (event) => {
      //prevent bubbling
      event.stopPropagation();
      
      await fetch(`/api/item/${item.id}`, {
        method: "PATCH"
      });
      
      itemNameElement.style.textDecoration = "line-through";
    })

    let deleteBtnElement = document.createElement("button");
    deleteBtnElement.className = "btn";
    deleteBtnElement.innerText = "Delete";
    deleteBtnElement.addEventListener("click", async () => {
      let response = await fetch(`/api/item/${item.id}`, {

      method: "DELETE"
      });

        let responseBody = await response.json();

        if (responseBody.successful) {
          let itemToDelete = document.getElementById(`item-${item.id}`);
          itemToDelete.remove();
       
        } 
        
        console.log(responseBody);
      });

    itemsContainer.appendChild(itemElement);
    itemElement.appendChild(itemNameContainerElement);
    itemElement.appendChild(deleteBtnElement);
    itemNameContainerElement.appendChild(itemNameElement);
  });
}

{/* <div class="item">
<div class="item-name-container">
  <div class="item-name">2 Sliced Cheese</div>
</div>
<button class="btn btn-primary">Delete</button>
</div> */}

let addItemFormElement = document.getElementById("add-item-form");
addItemFormElement.addEventListener("submit", async (event) => {
  event.preventDefault();

  let nameToCreateElement = document.getElementById("name-to-create");
  let qtyToCreateElement = document.getElementById("qty-to-create");

  let response = await fetch("/api/item", {
    method: "POST",
    body: JSON.stringify({
      "name": nameToCreateElement.value,
      "qty": qtyToCreateElement.value
    })
  });

  let responseBody = await response.json();
  console.log(responseBody);

  itemsContainer.innerHTML = "";
       items = await getAllItems();
       displayItems();

  nameToCreateElement.value = "";
  qtyToCreateElement.value = "";

})

const body = document.querySelector("body"),
modeToggle = document.querySelector(".dark-light");

modeToggle.addEventListener("click", () => {
    modeToggle.classList.toggle("active");
    body.classList.toggle("dark");

    if (!body.classList.contains("dark")) {
        
        localStorage.setItem("mode", "light-mode");
    } else {
        
        localStorage.setItem("mode", "dark-mode");
    }
});


