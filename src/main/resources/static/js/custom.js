$(document).ready(function () {
    $('input[type="text"]').attr("autocomplete", "off");
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e){
                $('#preview').attr({'src': e.target.result}).css("display","block");
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#file").change(function () {
        readURL(this);
    });
    function readURL2(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e){
                $('#preview2').attr({'src': e.target.result}).css("display","block");
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#file2").change(function () {
        readURL2(this);
    });
    function readURL3(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e){
                $('#preview3').attr({'src': e.target.result}).css("display","block");
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#file3").change(function () {
        readURL3(this);
    });
});

function deleteme(id){
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then(function(result) {
        if (result.value) {
            window.location.href = "http://localhost:8080/del/"+id;
        }
    });
}
function deletemeCate(id){
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then(function(result) {
        if (result.value) {
            window.location.href = "http://localhost:8080/del_cate/"+id;
        }
    });
}
var count=1;
$("#switchFragment").on('click',function () {
    count++;
    if(count%2==0){
        $('#navbar-replace').load('navbar-dropdown');
    }else{
        $('#navbar-replace').load('navbar');
    }
});

