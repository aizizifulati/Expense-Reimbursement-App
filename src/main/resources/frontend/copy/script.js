let logout=document.getElementById("logout-btn");
logout.addEventListener("click",function(){ let response = fetch("http://localhost:9000/api/logout",{
    method:"DELETE"
},
window.location.href="./"

)})

async function createNew(e){
    e.preventDefault(); //this prevents form onsubmit from refreshing

    let amount = document.getElementById("amount");
    let description = document.getElementById("description");
    let type_id = document.getElementById("type");
    // let roleInputElem = document.getElementById("role-input");
    // let form=document.getElementById("login-form");
   

    console.log(data.amount)
let myFile=document.getElementById("file")
let reader = new FileReader();
let fileByteArray = [];
reader.readAsArrayBuffer(myFile);
reader.onloadend = function(evt) {
   if (evt.target.readyState == FileReader.DONE) {
      var arrayBuffer = evt.target.result,
         array = new Uint8Array(arrayBuffer);
      for (var i = 0; i < array.length; i++) {
         fileByteArray.push(array[i]);
      }
   }

   let data =new FormData();
   data.append("amount",document.getElementById("amount").value);
   data.append("description",document.getElementById("description").value);
   data.append("type_id",document.getElementById("type").value);
   data.append("file",fileByteArray.value)

    let response = await fetch("http://localhost:9000/api/createreimbursement",{
        method:"POST",
        body:data
    })
    
    

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
}}