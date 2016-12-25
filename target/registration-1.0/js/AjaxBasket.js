/**
 * Created by danil on 25.12.16.
 */

function removeFromBucket() {
    var xmlhttp;
    if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {                                                  //for IE6,IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        if(xmlhttp.readyState==4 && xmlhttp.status == 200){
            document.getElementById("basket").innerHTML=xmlhttp.responseText ;
        }
    };
    xmlhttp.open("POST", "/basket", true);
    xmlhttp.send("");
}
