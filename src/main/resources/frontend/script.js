window.addEventListener("load", async () => {
    let response = await fetch("http://localhost:9000/api/check-session");
    let result = await response.json();

    if(result.successful){
        window.location.href = `./${result.data.role.toLowerCase()}-dashboard`
    }
})

async function login(e){
    e.preventDefault(); //this prevents form onsubmit from refreshing

    let usernameInputElem = document.getElementById("username-input");
    let passwordInputElem = document.getElementById("password-input");
    // let roleInputElem = document.getElementById("role-input");

    let response = await fetch("http://localhost:9000/api/login",{
        method: "POST",
        body: JSON.stringify({
            username: usernameInputElem.value,
            password: passwordInputElem.value,
            
        })
    })

    let result = await response.json();

    if(result.successful){
        window.location.href = `./${result.data.role.toLowerCase()}-dashboard`
    }else{
        //some logic if login was unsuccessful
    }

}