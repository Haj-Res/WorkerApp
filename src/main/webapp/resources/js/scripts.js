function fadeElement(id) {
    var elem = document.getElementById(id);
    if (elem !== null) {
        setTimeout(function () {
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
}