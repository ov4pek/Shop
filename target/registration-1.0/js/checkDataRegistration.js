/**
 * Created by Admin on 18.10.2016.
 */

// function validate() {
//     var element = document.querySelector(".validate");
//     var loginField = document.getElementById("login");
//     var login = loginField.value;
//     var password = document.getElementById("password").value;
//     var repassword = document.getElementById("repassword").value;
//     var name = document.getElementById("fullname").value;
//
//     element.addEventListener("submit", function (event) {
//         if (!validateEmail(login) || password.length < 6 || repassword.length < 6 || !(password === repassword) || name == null || name == '') {
//             event.preventDefault();
//         } else {
//             document.getElementById("validate").submit();
//         }
//         if (!validateName(name)) {
//             document.getElementById("fullname").style.borderColor = 'red';
//         } else {
//             document.getElementById("fullname").style.borderColor = '';
//         }
//         if (name == "" || name == null) {
//             document.getElementById("fullname").style.borderColor = 'red';
//         } else {
//             document.getElementById("fullname").style.borderColor = '';
//         }
//         if (password.length < 6) {
//             document.getElementById("password").style.borderColor = 'red';
//         } else {
//             document.getElementById("password").style.borderColor = '';
//         }
//         if (repassword.length < 6) {
//             document.getElementById("repassword").style.borderColor = 'red';
//         } else {
//             document.getElementById("repassword").style.borderColor = '';
//         }
//         if (!validateEmail(login)) {
//             loginField.style.borderColor = "red";
//         } else {
//             loginField.style.borderColor = "";
//         }
//
//     })
// }
// function validateName(name) {
//     regular = /^[a-zA-Z\s{,2}]{0,50}$/
//     return regular.test(name);
// }
// function validateEmail(email) {
//     regular = /^[a-zA-Z0-9_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,8}$/;
//     return regular.test(email);
// }

$(function () {
    $("#registr").validate({
        rules: {
            fullname: {
                required: true,
                minlength: 1
            },
            login: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            },
            repassword: {
                required: true,
                minlength: 3,
                equalTo: "#password"
            }
        },
        messages: {
            fullname: {
                required: "Заполните поле",
                minlength: "Поле должно содержать не менее одного символа"
            },
            login: {
                required: "Заполните поле",
                email: "Введенный e-mail некорректен",
            },
            password: {
                required: "Заполните поле",
                minlength: "Поле должно содержать не менее 6 символов"
            },
            repassword: {
                required: "Заполните поле",
                equalTo: "Пароли должны совпадать",
                minlength: "Поле должно содержать не менее 6 символов"
            }

        },
        focusCleanup: true,
        focusInvalid: false

    });
});

