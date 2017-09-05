
		//tab
		function sethighlight(n){
		 var tabDiv = document.getElementById('tab_container_div');
		   var getLis = tabDiv.getElementsByTagName('li');
		   for(var i=0;i<getLis.length;i++){
		    getLis[i].className = 'cls_tab_title_li';
		    }
				document.getElementById('li_Div'+(n+1)).className = 'cls_tab_title_li_first';
		}
		var divs = new Array('div_show1','div_show2','div_show3','div_show4','div_show5','div_show6','div_show7','div_show8','div_show9');
		function showDiv(id){
		  for(var i=0;i<divs.length;i++){
		    var k = divs[i];
		    if(document.getElementById(k)){
		      document.getElementById(k).style.display = (k==id?'block':'none');
		    }
		  }
		  window.scrollTo(0,screen.availHeight);
		  $("tabIndex").value = this.id;
		}