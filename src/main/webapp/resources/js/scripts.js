function fadeElement(id) {
    setTimeout(function () {
        var elem = document.getElementById(id);
        var fadeInterval = setInterval(function () {
            if (!elem.style.opacity) {
                elem.style.opacity = 1;
            }
            if (elem.style.opacity > 0) {
                elem.style.opacity -= 0.1;
            }
            if (elem.style.opacity <= 0) {
                elem.style.display = 'none';
                clearInterval(fadeInterval);
            }
        }, 15);
    }, 5000)
}