function toggleButton(element) {

	var pressed = (element.getAttribute("aria-pressed") === "true");
	//alert(element.getAttribute("id") + ' ' + element.getAttribute("aria-pressed"));
	element.setAttribute("aria-pressed", !pressed);
	element.classList.toggle("active");
}

function toggleVisibility(element) {

	var pressed = (element.getAttribute("aria-pressed") === "true");
	//alert(element.getAttribute("id") + ' ' + element.getAttribute("aria-pressed"));
	element.setAttribute("aria-pressed", !pressed);
	element.classList.toggle("active");
}