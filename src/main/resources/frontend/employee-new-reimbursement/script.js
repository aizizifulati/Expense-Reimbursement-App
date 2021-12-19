let logout=document.getElementById("logout-btn");

logout.addEventListener("click",function(){ let response = fetch("http://localhost:9000/api/logout",{
    method:"DELETE"
},
window.location.href="../"

)})

async function createNew(e){
    e.preventDefault(); //this prevents form onsubmit from refreshing

    let amount = document.getElementById("amount");
    let description = document.getElementById("description");
    let type_id = document.getElementById("type");
    // let roleInputElem = document.getElementById("role-input");
    // let form=document.getElementById("login-form");
    let data =new FormData();
    data.append("amount",document.getElementById("amount").value);
    data.append("description",document.getElementById("description").value);
    data.append("type_id",document.getElementById("type").value);

    console.log(data.amount)

    

    let response = await fetch("http://localhost:9000/api/createreimbursement",{
        method:"POST",
        body:data
    })

    console.log(response)
    if (response.headers.status=200){
        window.alert("you have created a new Reimbursement !");
        window.location.href="../employee-reimbursments"
    }
    
    

    // let response = await fetch("http://localhost:9000/api/login",{
    //     method:"POST",
    //     body:data
    // })
    

    // let result = await response.json();

    // if(result.successful )
    // {
    //     switch (result.data.role_id){
    //         case 1:
    //             window.location.href=`./employee-dashboard`;
    //             break;
    //         case 2:
    //             window.location.href=`./manager-dashboard`;
    //             break;

    //     }}}
}