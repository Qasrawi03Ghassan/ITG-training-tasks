const form = document.getElementById('form');
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
});