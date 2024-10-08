let itemsContainer;
let user;
let items;
window.addEventListener("load", async () => {
  let getUsername = localStorage.getItem("userName");

  let usernameElement = document.getElementById("dashboard");
  usernameElement.innerText = "Welcome " + getUsername;
  usernameElement.style.display = "block";
  let response = await fetch("/api/session");
  let responseBody = await response.json();

  if (!responseBody.successful) {
    window.location = "../";
  }

  user = responseBody.data;

  items = await getAllItems();
  console.log(displayItems());
  console.log(items);
});

async function getAllItems() {
  let response = await fetch("/api/item");
  let responseBody = await response.json();
  return responseBody.data;
}

//   window.history.pushState(null, null, window.location.href);
//   window.onpopstate = function () {
//     window.history.go(1);
//   };

let logoutBtn = document.getElementById("logout-btn");
logoutBtn.addEventListener("click", () => {
  fetch("/api/session", { method: "DELETE" });

  window.location = "../";
});

// <div class="name-btn-container">
//   <div class="item-container">
//     <div class="item-name-container">
//       <div class="item-name">Milk</div>
//     </div>
//   </div>
//   <button id="delete-btn" class="btn">Delete</button>
// </div>
async function displayItems() {
  items.forEach((item) => {
    itemsContainer = document.getElementById("items-container");
    let nameBtnContainerElement = document.createElement("div");
    nameBtnContainerElement.className = "name-btn-container";
    nameBtnContainerElement.id = `item-${item.id}`;

    let itemContainerElement = document.createElement("div");
    itemContainerElement.className = "item-container";

    let itemNameContainerElement = document.createElement("div");
    itemNameContainerElement.className = "item-name-container";

    let itemNameElement = document.createElement("div");
    itemNameElement.className = "item-name";
    itemNameElement.innerText = `${item.qty} ${item.name}`;

    if (item.inCart) {
      itemNameElement.style.textDecoration = "line-through";
    }

    itemNameElement.addEventListener("click", async (event) => {
      event.stopPropagation();

      await fetch(`/api/item/${item.id}`, {
        method: "PATCH",
      });
      itemNameElement.style.textDecoration = "line-through";
    });

    let deleteBtnElement = document.createElement("button");
    deleteBtnElement.className = "btn";
    deleteBtnElement.innerText = "Delete";

    nameBtnContainerElement.appendChild(itemContainerElement);
    nameBtnContainerElement.appendChild(deleteBtnElement);

    itemContainerElement.appendChild(itemNameContainerElement);
    itemContainerElement.appendChild(itemNameElement);

    itemNameContainerElement.appendChild(itemNameElement);

    itemsContainer.appendChild(nameBtnContainerElement);

    deleteBtnElement.addEventListener("click", async () => {
      let response = await fetch(`/api/item/${item.id}`, {
        method: "DELETE",
      });

      let responseBody = await response.json();

      console.log(responseBody);

      if (responseBody.successful) {
        let itemToDelete = document.getElementById(`item-${item.id}`);
        itemToDelete.remove();
      }
    });

    console.log(item);

    itemNameElement.addEventListener("click", () => {
      console.log("event triggered");
    });
  });
}

let addItemFormElement = document.getElementById("add-item-form");
addItemFormElement.addEventListener("submit", async (event) => {
  event.preventDefault();

  let nameToCreateElement = document.getElementById("name-to-create");
  let qtyToCreateElement = document.getElementById("qty-to-create");

  let response = await fetch("/api/item", {
    method: "POST",
    body: JSON.stringify({
      name: nameToCreateElement.value,
      qty: qtyToCreateElement.value,
    }),
  });

  let responseBody = await response.json();
  console.log(responseBody);

  itemsContainer.innerHTML = "";
  items = await getAllItems();
  displayItems();
});

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
