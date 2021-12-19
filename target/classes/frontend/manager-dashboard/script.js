window.onload = async () => {
    let response = await fetch("http://localhost:9000/api/check-session");
    let result = await response.json();
    if(!result.successful)
    window.location.href = "../"
    console.log(result.data)

    if(result.data.role_id==1)
    window.location.href = "../employee-dashboard"

    

    switch (result.data.role_id){
        case 1:
            role_id_show="Employee"
            break;
        case 2:
            role_id_show="Manager"
            break;}

    let mainContainer1 =document.getElementById("myData");
    let div1 =document.createElement("div");
    div1.innerHTML='User name : ' +result.data.username;
    mainContainer1.appendChild(div1);

    let mainContainer2 =document.getElementById("myData");
    let div2 =document.createElement("div");
    div2.innerHTML='User ID : ' +result.data.user_id;
    mainContainer2.appendChild(div2);

    let mainContainer3 =document.getElementById("myData");
    let div3 =document.createElement("div");
    div3.innerHTML='First Name: ' +result.data.first_name;
    mainContainer3.appendChild(div3);

    let mainContainer4 =document.getElementById("myData");
    let div4 =document.createElement("div");
    div4.innerHTML='Last Name: ' +result.data.last_name;
    mainContainer4.appendChild(div4);

    let mainContainer5 =document.getElementById("myData");
    let div5 =document.createElement("div");
    div5.innerHTML='Email: ' +result.data.email;
    mainContainer5.appendChild(div5);

    let mainContainer6 =document.getElementById("myData");
    let div6 =document.createElement("div");
    div6.innerHTML='Your role: ' +role_id_show;
    mainContainer6.appendChild(div6);



    let logout=document.getElementById("logout-btn");
    logout.addEventListener("click",function(){ let response = fetch("http://localhost:9000/api/logout",{
        method:"DELETE"
    },
    window.location.href="../"
    
    )})

    // let myrem=document.getElementById("rem-btn");
    // myrem.addEventListener("click",function(){ 
    // window.location.href="../employee-reimbursments"
    
    // })

    let approved=document.getElementById("approved-btn");
    approved.addEventListener("click",function(){ 
    window.location.href="../manager-approved"
    
    })

    
    
  




   
}