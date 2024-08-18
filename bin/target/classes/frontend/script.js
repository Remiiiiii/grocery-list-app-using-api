
//http://localhost:9000 was removed after deploying to codepipeline

window.onload = async () => {
    let registerBtnElement = document.getElementById("register-btn")
    let response = await fetch("/api/session"); 
    let responseBody = await response.json();

    if(responseBody.successful) {
        window.location = "./dashboard";
        registerBtnElement.style.visibility = 'hidden';
    }
}

let loginFormElement = document.getElementById("login-form");


loginFormElement.addEventListener("submit", (event) => {
    event.preventDefault();
    let usernameInputElement = document.getElementById("username-input");
    let passwordInputElement = document.getElementById("password-input");
    let username = document.getElementById("username-input").value;
    localStorage.setItem("userName", username);

sendLoginRequest(usernameInputElement.value, passwordInputElement.value);
});

async function sendLoginRequest(username, password) {
    let response = await fetch("/api/session", 
    {
        method: "POST",
        body: JSON.stringify({
            "username": username,
            "password": password
        })
    });

    let responseBody = await response.json();

    console.log(responseBody);

    if (responseBody.successful) {
        window.location = "./dashboard";
    } else {
        let messageElement = document.getElementById("validation");
        messageElement.innerText = responseBody.message;
        messageElement.style.display ='block';
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



