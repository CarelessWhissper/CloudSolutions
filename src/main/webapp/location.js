document.addEventListener('DOMContentLoaded', function() {
    fetchLocations(); // Fetch locations when the DOM content is loaded
    fetchTestimonials(); // Fetch testimonials when the DOM content is loaded
});


async function fetchTestimonials() {
    try {
        const response = await fetch('http://localhost:8080/myapp/api/reviews');
        if (!response.ok) {
            throw new Error(`Failed to fetch testimonials: ${response.status} ${response.statusText}`);
        }
        const testimonials = await response.json();
        populateTestimonials(testimonials);
    } catch (error) {
        console.error('Error fetching testimonials:', error);
    }
}


function populateTestimonials(testimonialsData) {
    const carouselInner = document.querySelector('.carousel-inner');
    carouselInner.innerHTML = ''; // Clear existing content

    testimonialsData.forEach((testimonial, index) => {
        const itemClass = index === 0 ? 'carousel-item active' : 'carousel-item';
        const testimonialItem = `
            <div class="${itemClass}">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${testimonial.user.naam}</h5>
                        <p class="card-text">${testimonial.reviewText}</p>
                        <p class="card-text">${'★'.repeat(Math.round(testimonial.rating))}</p>
                        <p class="card-text">Location: ${testimonial.location.name}</p>
                    </div>
                </div>
            </div>
        `;
        carouselInner.insertAdjacentHTML('beforeend', testimonialItem);
    });
}


async function fetchLocations() {
    try {
        const locationIds = [1, 2]; // Specify the IDs of the locations you want to fetch
        const uniqueLocations = await Promise.all(locationIds.map(id => fetchLocationById(id)));
        populateLocations(uniqueLocations);
    } catch (error) {
        console.error('Error fetching locations:', error);
    }
}

async function fetchLocationById(id) {
    try {
        const response = await fetch(`http://localhost:8080/myapp/api/locations/${id}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch location ${id}: ${response.status} ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error(`Error fetching location ${id}:`, error);
        return null;
    }
}

function populateLocations(locations) {
    const locationsContainer = document.getElementById('locationsContainer');
    locationsContainer.innerHTML = ''; // Clear existing content

    locations.forEach(location => {
        if (location) { // Check if location exists (not null)
            const card = `
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">Name:</h5>
                            <p class="card-text">${location.name}</p>
                            <h5 class="card-title fw-bold">Description:</h5>
                            <p class="card-text">${location.description}</p>
                            <h5 class="card-title fw-bold">Address:</h5>
                            <p class="card-text">${location.address}</p>
                            <h5 class="card-title fw-bold">Type:</h5>
                            <p class="card-text">${location.type}</p>
                            <h5 class="card-title fw-bold">Rating:</h5>
                            <p class="card-text">${location.rating}</p>
                        </div>
                    </div>
                </div>
            `;
            locationsContainer.insertAdjacentHTML('beforeend', card);
        }
    });
}

function submitReview() {
    const reviewForm = document.getElementById('reviewForm');
    reviewForm.addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent form submission

        // Get form data
        const reviewText = document.getElementById('reviewText').value.trim();
        const rating = document.getElementById('rating').value;

        // Hardcoded user ID and location ID
        const userId = 2;
        const locationId = 1;

        // Prepare review object with hardcoded IDs
        const reviewData = {
            reviewText: reviewText,
            rating: rating,
            user: {
                id: userId
            },
            location: {
                id: locationId
            }
        };

        // Send AJAX request to save review
        fetch('http://localhost:8080/myapp/api/reviews/post', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reviewData)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to submit review');
                }
                return response.json();
            })
            .then(data => {
                console.log('Review submitted successfully:', data);
                // Optionally, you can display a success message or update the UI
                // Clear the form
                reviewForm.reset();
            })
            .catch(error => {
                console.error('Error submitting review:', error);
                // Optionally, you can display an error message or handle the error
            });
    });
}


