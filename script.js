// Predefined users
let users = [
    { username: "student1", password: "1234", role: "Student" },
    { username: "faculty1", password: "1234", role: "Faculty" }
];

let currentUser = null;
let items = [];
let uploadedImage = "";

// LOGIN FUNCTION
function login() {

    let role = document.getElementById("role").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    let user = users.find(u =>
        u.username === username &&
        u.password === password &&
        u.role === role
    );

    if (user) {
        currentUser = user;

        document.getElementById("loginSection").classList.add("hidden");
        document.getElementById("mainSection").classList.remove("hidden");

        document.getElementById("welcomeText").innerText =
            "Welcome " + user.username + " (" + user.role + ")";
    }
    else {
        alert("Invalid credentials!");
    }
}

// LOGOUT FUNCTION
function logout() {

    currentUser = null;

    document.getElementById("mainSection").classList.add("hidden");
    document.getElementById("loginSection").classList.remove("hidden");
}

// IMAGE PREVIEW
function previewImage(event) {

    const reader = new FileReader();

    reader.onload = function() {

        uploadedImage = reader.result;

        document.getElementById("preview").innerHTML =
            `<img src="${uploadedImage}">`;

    }

    reader.readAsDataURL(event.target.files[0]);
}

// ADD ITEM
function addItem() {

    let type = document.getElementById("type").value;
    let name = document.getElementById("itemName").value;
    let description = document.getElementById("description").value;
    let location = document.getElementById("location").value;

    if (!name || !description || !location) {
        alert("Please fill all fields");
        return;
    }

    let item = {
        type,
        name,
        description,
        location,
        postedBy: currentUser.username,
        image: uploadedImage
    };

    items.push(item);

    displayItems();
    clearForm();
}

// DISPLAY ITEMS
function displayItems() {

    let list = document.getElementById("itemsList");
    list.innerHTML = "";

    items.forEach(item => {

        list.innerHTML += `
        <div class="item">

        <strong>${item.type}</strong><br>

        <b>Name:</b> ${item.name}<br>
        <b>Description:</b> ${item.description}<br>
        <b>Location:</b> ${item.location}<br>
        <b>Posted By:</b> ${item.postedBy}

        ${item.image ? `<img src="${item.image}">` : ""}

        </div>
        `;

    });
}

// CLEAR FORM
function clearForm() {

    document.getElementById("itemName").value = "";
    document.getElementById("description").value = "";
    document.getElementById("location").value = "";
    document.getElementById("imageUpload").value = "";
    document.getElementById("preview").innerHTML = "";

    uploadedImage = "";
}


// SEARCH FUNCTION (Linear Search)
function searchItems() {

    let input = document.getElementById("searchBar").value.toLowerCase();
    let list = document.getElementById("itemsList");

    list.innerHTML = "";

    items.forEach(item => {

        if (item.name.toLowerCase().includes(input)) {

            list.innerHTML += `
            <div class="item">

            <strong>${item.type}</strong><br>

            <b>Name:</b> ${item.name}<br>
            <b>Description:</b> ${item.description}<br>
            <b>Location:</b> ${item.location}<br>
            <b>Posted By:</b> ${item.postedBy}

            ${item.image ? `<img src="${item.image}">` : ""}

            </div>
            `;
        }

    });

}