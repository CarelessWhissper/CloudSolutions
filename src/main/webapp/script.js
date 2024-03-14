
    document.addEventListener('DOMContentLoaded', function() {
    // Get all card elements
    var cards = document.querySelectorAll('.card');

    // Loop through each card
    cards.forEach(function(card, index) {
    // Add click event listener to each card
    card.addEventListener('click', function() {
    // Hide all sections
    var sections = document.querySelectorAll('section');
    sections.forEach(function(section) {
    section.style.display = 'none';
});

    // Show the corresponding section based on the index
    var sectionToShow = document.querySelectorAll('section')[index];
    sectionToShow.style.display = 'block';
});
});
});

