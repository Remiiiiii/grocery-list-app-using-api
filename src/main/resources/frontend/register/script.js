window.onload = async () => {
  let response = await fetch("/api/session");
  let responseBody = await response.json();

  if (responseBody.successful) {
    window.location = "../dashboard";
  }
};

let registerFormElement = document.getElementById("register-form");

registerFormElement.addEventListener("submit", (event) => {
  event.preventDefault();

  let credentials = {
    username: document.getElementById("username-input").value,
    password: document.getElementById("password-input").value,
    firstname: document.getElementById("firstname-input").value,
    lastname: document.getElementById("lastname-input").value,
  };

  sendRegistrationRequest(credentials);
});

async function sendRegistrationRequest(Usercredentials) {
  let response = await fetch("/api/user", {
    method: "POST",
    body: JSON.stringify(Usercredentials),
  });

  let responseBody = await response.json();
  console.log(responseBody);

  if (responseBody.successful) {
    window.location = "/";
  } else {
    let messageElement = document.getElementById("validation");
    messageElement.innerText = responseBody.message;
    messageElement.style.display = "block";
  }
}

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
