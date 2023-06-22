function showHide() {
    var x = document.getElementById("TrinhDoTiengNhat");
    if (x.style.display == "none") {
        x.style.display = "";
    } else {
        x.style.display = "none";
    }
}



function ConfirmDelete(message) {
	var confir = confirm(message);
	
	if(confir){
		var form =   document.getElementById("formDelete");
		form.action = "DeleteUserConfirm.do";
		form.method = "POST";
		form.submit();
	}
	  
	  
	  
  
}
