// ===================================
// ROLFING WEB PROJECT - JAVASCRIPT
// ===================================

document.addEventListener('DOMContentLoaded', function() {
    initializeEventListeners();
    initializeScrollAnimations();
    initializeFormValidation();
});

// ===================================
// EVENT LISTENERS
// ===================================

function initializeEventListeners() {
    // Cerrar navbar al hacer click en un link
    const navLinks = document.querySelectorAll('.navbar-nav a');
    const navbarCollapse = document.querySelector('.navbar-collapse');
    
    navLinks.forEach(link => {
        link.addEventListener('click', function() {
            if (navbarCollapse.classList.contains('show')) {
                navbarCollapse.classList.remove('show');
            }
        });
    });

    // Smooth scroll para links internos
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function(e) {
            const href = this.getAttribute('href');
            if (href !== '#' && document.querySelector(href)) {
                e.preventDefault();
                const target = document.querySelector(href);
                target.scrollIntoView({ behavior: 'smooth' });
            }
        });
    });

    // Navbar shadow on scroll
    window.addEventListener('scroll', function() {
        const navbar = document.querySelector('.navbar');
        if (window.scrollY > 50) {
            navbar.style.boxShadow = '0 4px 12px rgba(0, 0, 0, 0.15)';
        } else {
            navbar.style.boxShadow = '0 2px 8px rgba(0, 0, 0, 0.1)';
        }
    });

    // Contact Form Handler - DESHABILITADO
    // El formulario es manejado por el script inline en index.html
    // para evitar conflictos entre múltiples handlers
    console.log('✅ Script cargado (formulario manejado por inline script en index.html)');
}

// ===================================
// SCROLL ANIMATIONS
// ===================================

function initializeScrollAnimations() {
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -100px 0px'
    };

    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
                observer.unobserve(entry.target);
            }
        });
    }, observerOptions);

    // Animar tarjetas de video
    document.querySelectorAll('.video-card').forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(20px)';
        card.style.transition = 'all 0.6s ease';
        observer.observe(card);
    });

    // Animar tarjetas de testimonios
    document.querySelectorAll('.testimonial-card').forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(20px)';
        card.style.transition = 'all 0.6s ease';
        observer.observe(card);
    });

    // Animar feature boxes
    document.querySelectorAll('.feature-box').forEach(box => {
        box.style.opacity = '0';
        box.style.transform = 'translateY(20px)';
        box.style.transition = 'all 0.6s ease';
        observer.observe(box);
    });

    // Animar contact cards
    document.querySelectorAll('.contact-card').forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(20px)';
        card.style.transition = 'all 0.6s ease';
        observer.observe(card);
    });
}

// ===================================
// FORM VALIDATION & SUBMISSION
// ===================================

function initializeFormValidation() {
    // La validación del formulario de contacto se maneja en handleFormSubmit
    // Evitar validación de Bootstrap que interfiere con fetch
    const forms = document.querySelectorAll('form:not(#contactForm)');
    
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            // Siempre prevenir submit nativo - el contactForm usa fetch, el resto no hace nada
            e.preventDefault();
            e.stopPropagation();
            form.classList.add('was-validated');
        }, false);
    });
}

function handleFormSubmit(form) {
    // Validar campos requeridos
    const nombre = form.querySelector('#nombre').value.trim();
    const email = form.querySelector('#email').value.trim();
    const mensaje = form.querySelector('#mensaje').value.trim();
    const asunto = form.querySelector('#asunto') ? form.querySelector('#asunto').value.trim() : '';
    const telefono = form.querySelector('#telefono') ? form.querySelector('#telefono').value.trim() : '';

    if (!nombre || !email || !mensaje) {
        showAlert('Por favor completa todos los campos requeridos.', 'warning');
        return;
    }

    // Validar email
    if (!isValidEmail(email)) {
        showAlert('Por favor ingresa un email válido.', 'warning');
        return;
    }

    // Deshabilitar botón
    const submitBtn = form.querySelector('button[type="submit"]');
    const originalText = submitBtn.innerHTML;
    submitBtn.disabled = true;
    submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Enviando...';

    // Preparar datos del formulario
    const formData = new FormData();
    formData.append('nombre', nombre);
    formData.append('email', email);
    formData.append('telefono', telefono);
    formData.append('asunto', asunto);
    formData.append('mensaje', mensaje);

    // Construir URL correcta del API
    const currentPath = window.location.pathname;
    let apiUrl = '/api/contact';
    
    // Si estamos en /rolfing o subsecuente, usar la ruta con /rolfing
    if (currentPath.includes('/rolfing')) {
        apiUrl = '/rolfing/api/contact';
    }
    
    console.log('📤 Enviando a:', apiUrl);
    console.log('📍 Ubicación actual:', window.location.href);
    
    // Enviar a través de fetch
    fetch(apiUrl, {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        submitBtn.disabled = false;
        submitBtn.innerHTML = originalText;
        
        if (data.success) {
            showAlert('¡Mensaje enviado exitosamente! Nos pondremos en contacto pronto.', 'success');
            form.reset();
            form.classList.remove('was-validated');
        } else {
            showAlert(data.message || 'Error al enviar el mensaje.', 'danger');
        }
    })
    .catch(error => {
        console.error('❌ Error de conexión:', error);
        console.error('📍 URL intentada:', apiUrl);
        console.error('🔍 Detalles:', error.message);
        submitBtn.disabled = false;
        submitBtn.innerHTML = originalText;
        showAlert('Error de conexión: ' + error.message, 'danger');
    });
}

function isValidEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}

function showAlert(message, type = 'info') {
    // Crear contenedor de alerta
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.setAttribute('role', 'alert');
    alertDiv.style.position = 'fixed';
    alertDiv.style.top = '20px';
    alertDiv.style.right = '20px';
    alertDiv.style.zIndex = '9999';
    alertDiv.style.minWidth = '300px';
    alertDiv.style.maxWidth = '500px';
    alertDiv.style.boxShadow = '0 4px 12px rgba(0, 0, 0, 0.15)';
    
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;

    document.body.appendChild(alertDiv);

    // Auto-remove después de 5 segundos
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

// ===================================
// UTILITIES
// ===================================

// Función para obtener parámetros de URL
function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    const regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    const results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

// Función para animar números
function animateValue(element, start, end, duration) {
    let startTimestamp = null;
    const step = (timestamp) => {
        if (!startTimestamp) startTimestamp = timestamp;
        const progress = Math.min((timestamp - startTimestamp) / duration, 1);
        element.textContent = Math.floor(progress * (end - start) + start);
        if (progress < 1) {
            window.requestAnimationFrame(step);
        }
    };
    window.requestAnimationFrame(step);
}

// Función para agregar clase en scroll
function addClassOnScroll(selector, className) {
    const elements = document.querySelectorAll(selector);
    
    const observer = new IntersectionObserver(entries => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add(className);
            }
        });
    }, { threshold: 0.5 });

    elements.forEach(element => {
        observer.observe(element);
    });
}

// ===================================
// INICIALIZACIÓN EN CARGA
// ===