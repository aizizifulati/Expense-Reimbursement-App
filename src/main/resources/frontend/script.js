window.addEventListener("load", async () => {
    let response = await fetch("http://localhost:9000/api/check-session");
    let result = await response.json();

    if(result.successful){
        switch (result.data.role_id){
            case 1:
                window.location.href=`./employee-dashboard`;
                break;
            case 2:
                window.location.href=`./manager-dashboard`;
                break;
    }}
})

async function login(e){
    e.preventDefault(); //this prevents form onsubmit from refreshing

    let username = document.getElementById("username-input");
    let password = document.getElementById("password-input");
    // let roleInputElem = document.getElementById("role-input");
    // let form=document.getElementById("login-form");
    let data =new FormData();
    data.append("username",document.getElementById("username-input").value);
    data.append("password",document.getElementById("password-input").value);
    

    let response = await fetch("http://localhost:9000/api/login",{
        method:"POST",
        body:data
    })

    if(response.status==400){ window.alert("Login username or password is not valid,please try again!!!!");}
    console.log(response.status)
    
    

    let result = await response.json();

    
    

    if(result.successful )
    {
        switch (result.data.role_id){
            case 1:
                window.location.href=`./employee-dashboard`;
                break;
            case 2:
                window.location.href=`./manager-dashboard`;
                break;

        }
        
        
    
    
    
    // if(result.data.role_id==1){window.location.href=`./employee-dashboard`}
    // else if(result.data.role_id==2){window.location.href=`./manager-dashboard`}
    // else{console.log("no!")}
            
     
            

    }
     if(response.status==400){ window.alert("Login username or password is not valid,please try again!!!!");}
    
    

}