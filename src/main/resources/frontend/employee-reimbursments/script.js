window.addEventListener("load", async () => {
    // let response = await fetch("http://localhost:9000/api/check-session");
    // let result = await response.json();


    let response=await fetch("http://localhost:9000/api/reimbursementsbyusername");
    let rt=await response.json();

    console.log(rt.data)


    let logout=document.getElementById("logout-btn");
    logout.addEventListener("click",function(){ let response = fetch("http://localhost:9000/api/logout",{
        method:"DELETE"
    },
    window.location.href="../"
    
    )})
    let remContainer=document.getElementById("rem-container");
    remContainer.innerHTML=""
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
            <span class="rem_type_id">Resolved Time :${rem.status_id==1?null:new Date(rem.resolved)}</span>
        </div>
        `
        remContainer.appendChild(remItemElem);
        
    });

})