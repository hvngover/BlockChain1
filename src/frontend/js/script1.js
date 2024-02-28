document.addEventListener('DOMContentLoaded', () => {
    // Event listener for form submission
    document.getElementById('startVotingForm').addEventListener('submit', initiateVotingEvent);

    // Event listener for connect button
    document.getElementById('connectButton').addEventListener('click', connectWallet);

    // Event listener for input change on election name
    document.getElementById('electionName').addEventListener('input', handleElectionNameChange);

    // Event listener for input change on start date and time
    document.getElementById('startDateTime').addEventListener('input', handleStartDateTimeChange);
});

// Function to handle form submission
async function initiateVotingEvent(event) {
    event.preventDefault();
    const electionName = document.getElementById('electionName').value;
    const startDateTime = document.getElementById('startDateTime').value;
    const endDateTime = document.getElementById('endDateTime').value;

    // Call the function to start a new election with the provided details
    await startElection(electionName, startDateTime, endDateTime);

    // Show a popup
    alert('Voting event initiated successfully!');
}

// Function to handle input change for election name
function handleElectionNameChange(event) {
    // You can add validation or additional logic here if needed
    console.log('Election name changed:', event.target.value);
}

// Function to handle input change for start date and time
function handleStartDateTimeChange(event) {
    // You can add validation or additional logic here if needed
    console.log('Start date and time changed:', event.target.value);
}
    