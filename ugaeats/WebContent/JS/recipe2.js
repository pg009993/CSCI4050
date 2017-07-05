function addIngredient() {
	var tag = "- <input class=\"input text steps\" type=\"text\" name=\"ingredients\"> <br />";
	document.getElementById("ingredients").innerHTML += tag;
}

function addStep() {
	var tag = "- <input class=\"input text steps\" type=\"text\" name=\"steps\"> <br />";
	document.getElementById("steps").innerHTML += tag;
}

$(document).ready(function(){
    
    $("#addIngredient").click(function(){
    	var div = $('<div></div>');
        var input = $('<input type="text" class="input text steps" name="ingredients"/>');
        div.append(input);
        div.append('<button type="button" class="delete">Delete</button>');
    	$("#ingredients").append(div);
    });
    
    $("#addStep").click(function(){
    	var div = $('<div></div>');
        var input = $('<input type="text" class="input text steps" name="steps"/>');
        div.append(input);
        div.append('<button type="button" class="delete">Delete</button>');
    	$("#steps").append(div);
    });
    
    $(document).on('click', '.delete', function(){
    	alert("delete");
    	$(this).parent().remove();
    });
});