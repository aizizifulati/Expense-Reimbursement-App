window.addEventListener("load", async () => {
    // let response = await fetch("http://localhost:9000/api/check-session");
    // let result = await response.json();
    let response = await fetch("http://localhost:9000/api/check-session");
    let result = await response.json();
    if(!result.successful)
    window.location.href = "../"
    console.log(result.data)

    if(result.data.role_id==1)
    window.location.href = "../employee-dashboard"



    let response1=await fetch("http://localhost:9000/api/getAllApproved");
    let rt=await response1.json();

    console.log(rt.data)


    let logout=document.getElementById("logout-btn");
    logout.addEventListener("click",function(){ let response = fetch("http://localhost:9000/api/logout",{
        method:"DELETE"
    },
    window.location.href="../"
    
    )})
    let remContainer=document.getElementById("rem-container");
    remContainer.innerHTML=" "
    // rt=JSON.parse(rt);

    rt.data.forEach(rem => {
        let remItemElem=document.createElement("div");
        remItemElem.className="rem-item";
        remItemElem.id=rem.id
        
        switch (rem.status_id){
                    case 1:
                        status_id_show="Pending"
                        break;
                    case 2:
                        status_id_show="Approved"
                        break;
                    case 3:
                        status_id_show="Denied"
                        break;}
        switch (rem.type_id){
                            case 1:
                                type_id_show="LODGING"
                                break;
                            case 2:
                                type_id_show="TRAVEL"
                                break;
                            case 3:
                                type_id_show="FOOD"
                                break;
                            case 4:
                                type_id_show="OTHER"
                                break;
                            
                            }

        remItemElem.innerHTML=`
        <div class="rem-info">
            <span class="rem_id">Reimbursement ID:${rem.id}</span>
            <span class="rem_description">Reimbursement Description:${rem.description}</span>
            <span class="rem_amount">Reimbursement Amount:${rem.amount}$</span>
            <span class="rem_submitted">reimbursement submited time:${new Date(rem.submitted)}</span>
            <span class="rem_status_id">Status :${status_id_show}</span>
            <span class="rem_type_id">Type :${type_id_show}</span>
            <span class="rem_type_id">Resolved By :${rem.resolver}</span>
            <span class="rem_type_id">Resolved Time :${rem.resolved?new Date(rem.resolved):null}</span>


            
        </div>
        `
        
        remContainer.appendChild(remItemElem);
        
        // function deny(a){
        //     console.log("this is deny"+a)
        // }

        // function approve(a){
        //     console.log("this is approve "+ a)
        // }
    });

})
function deny(a){
    console.log("this is deny"+a)
    let data =new FormData();
    data.append("id",a);
    let response = fetch("http://localhost:9000/api/denyareimbursement",{
        method:"POST",
        body:data,
    })
    window.location.href="./"
}

function approve(a){
    console.log("this is approve "+ a)
    let data =new FormData();
    data.append("id",a);
    let response = fetch("http://localhost:9000/api/approveareimbursement",{
        method:"POST",
        body:data,
    })
    window.location.href="./"
}