
$(window).scroll(
        function () {
            if ($(window).scrollTop() > 50) {
                $("#prueba").removeClass("navScroll2");
                $("#prueba").addClass("navScroll");
            } else {
                $("#prueba").removeClass("navScroll");
                $("#prueba").addClass("navScroll2");
            }
        }
);