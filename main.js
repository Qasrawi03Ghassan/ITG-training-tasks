//vanilla JavaScript code
/*const form = document.getElementById('form');
const usernameField = document.getElementById("u-field");
const passField = document.getElementById("pass-field");
const loginBtn = document.getElementById("btn");
const hidDiv = document.getElementById("hid-div");

const d1 = document.getElementById("danger1");
const d2 = document.getElementById("danger2");

const showPass = document.getElementById("eye");
const hidePass = document.getElementById("eye-d");

loginBtn.addEventListener("click",(event)=>{
    event.preventDefault();

    let userName = usernameField.value;
    let password = passField.value;

    if(userName === "")d1.style.display="inline";
    if(password === "")d2.style.display="inline";

    if(userName === "" || password === ""){
        hidDiv.style.display='block'
        setTimeout(()=>{hidDiv.style.display='none'},3000);
        hidDiv.style.color="red";

        hidDiv.textContent="Please fill all fields";
    }else if(userName === 'admin' && password === '12345'){
        hidDiv.style.display='block'

        hidDiv.style.color="lightGreen";
        hidDiv.textContent="Login Successful";

        loginBtn.textContent="Signing in...";
        loginBtn.disabled=true;

        setTimeout(()=>{form.submit()},2000);

    }else{
        event.preventDefault();

        hidDiv.style.display='block'
        setTimeout(()=>{hidDiv.style.display='none'},3000);
        hidDiv.style.color="red";

        hidDiv.textContent = "Invalid credentials";
    }
});

usernameField.addEventListener("click",()=>{
    d1.style.display="none";
});
passField.addEventListener("click",()=>{
    d2.style.display="none";
});

showPass.addEventListener("click",()=>{
    passField.type='text';
    showPass.style.display='none';
    hidePass.style.display='inline';
});
hidePass.addEventListener("click",()=>{
    passField.type='password';
    hidePass.style.display='none';
    showPass.style.display='inline';
});*/
//-----------------------------------------------------------------
//jQuery alternative code
$(()=>{ // {$(document).ready()} is deprecated so i used this instead
    const form = $('#form');

    const loginBtn = $('#btn');
    const usernameField = $('#u-field');
    const passwordField = $('#pass-field');
    const hidDiv = $('#hid-div');

    const showPass = $('#eye');
    const hidePass = $('#eye-d');

    const d1 = $('#danger1');
    const d2 = $('#danger2');

    loginBtn.on("click",(event)=>{
        event.preventDefault();

        let userName = usernameField.val();
        let password = passwordField.val();

        if(userName === '') d1.css('display','inline');
        if(password === '') d2.css('display','inline');

        if(userName === '' || password === ''){
            hidDiv.css('display','block');
            setTimeout(()=>{hidDiv.css('display','none')},3000);
            hidDiv.css('color','red');

            hidDiv.text("Please fill all fields");
        }
        else if(userName === 'admin' && password === '12345'){
            hidDiv.css("display","block");
            hidDiv.css('color','lightGreen');
            hidDiv.text("Login successful");

            loginBtn.attr('disabled',true);
            loginBtn.text('Signing in...');

            setTimeout(()=>{form.trigger('submit');},2000);
        }else{
            hidDiv.css('display','block');
            setTimeout(()=>{hidDiv.css('display','none')},3000);
            hidDiv.css('color','red');

            hidDiv.text("Invalid credentials");
        }
    });

    usernameField.on("input",()=>{
        d1.css('display','none');
    });
    passwordField.on("input",()=>{
        d2.css('display','none');
    });
    usernameField.on("click",()=>{
        d1.css('display','none');
    });
    passwordField.on("click",()=>{
        d2.css('display','none');
    });

    showPass.on("click",()=>{
        passwordField.attr('type','text')

        showPass.css('display','none');
        hidePass.css('display','inline');
    });
    hidePass.on("click",()=>{
        passwordField.attr('type','password')

        hidePass.css('display','none');
        showPass.css('display','inline');
    });
});