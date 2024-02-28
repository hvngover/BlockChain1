document.addEventListener('DOMContentLoaded', () => {
    // Function to connect wallet using MetaMask
    async function connectMetaMask() {
        if (typeof window.ethereum === 'undefined') {
            alert('MetaMask is not installed.');
            return;
        }
        try {
            await window.ethereum.request({ method: 'eth_requestAccounts' });
            const provider = new ethers.providers.Web3Provider(window.ethereum, 'any');
            const signer = provider.getSigner();
            console.log("Account:", await signer.getAddress());

            document.getElementById('startVotingForm').style.display = 'block';

            await loadLiveElections();
        } catch (error) {
            console.error("Error connecting MetaMask:", error);
        }
    }


    async function connectTrustWallet() {
        if (typeof window.ethereum === 'undefined') {
            alert('Trust Wallet is not installed.');
            return;
        }
        try {
            await window.ethereum.request({ method: 'eth_requestAccounts' });
            const provider = new ethers.providers.Web3Provider(window.ethereum, 'any');
            const signer = provider.getSigner();
            console.log("Account:", await signer.getAddress());
            // Enable additional UI elements after successful connection
            document.getElementById('startVotingForm').style.display = 'block';
            // Call function to load live elections
            await loadLiveElections();
        } catch (error) {
            console.error("Error connecting Trust Wallet:", error);
        }
    }

    // Function to load live elections
    async function loadLiveElections() {
        const response = fetch('/getElectionResult', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        // Display live elections in the UI
        console.log("Loading live elections...");
    }

    // Function to start a new election
    async function startElection(name, startDateTime, endDateTime) {
        // Implement your logic to start an election here
        console.log("New election started:", name, startDateTime, endDateTime);
        // Reload live elections after starting a new one
        await loadLiveElections();
        // Show notification
        document.getElementById('notification').style.display = 'block';
    }

    // Function to handle form submission to start a new election
    async function handleStartElection(event) {
        event.preventDefault();
        const electionName = document.getElementById('electionName').value;
        const startDateTime = document.getElementById('startDateTime').value;
        const endDateTime = document.getElementById('endDateTime').value;
        // Call function to start election with form data
        await startElection(electionName, startDateTime, endDateTime);
    }

    // Event listener for connecting MetaMask wallet
    document.getElementById('connectMetaMaskButton').addEventListener('click', connectMetaMask);

    // Event listener for connecting Trust Wallet
    document.getElementById('connectTrustWalletButton').addEventListener('click', connectTrustWallet);

    // Event listener for starting a new election
    document.getElementById('startVotingForm').addEventListener('submit', handleStartElection);

    // Event listener for clearing form inputs
    document.getElementById('clearFormButton').addEventListener('click', () => {
        document.getElementById('startVotingForm').reset();
    });

    // Event listener for toggling form visibility
    document.getElementById('toggleFormVisibilityButton').addEventListener('click', () => {
        const form = document.getElementById('startVotingForm');
        form.style.display = form.style.display === 'none' ? 'block' : 'none';
    });
});

document.addEventListener('DOMContentLoaded', () => {
    // Function to handle form submission
    document.getElementById('voteForm').addEventListener('submit', (event) => {
        event.preventDefault();
        const voterID = document.getElementById('voterID').value;
        const voteOption = document.getElementById('voteOption').value;
        
        if (voterID.trim() === '') {
            alert('Please enter your voter ID.');
            return;
        }

        // TODO: Submit vote to the blockchain

        // Show notification
        const notification = document.getElementById('notification');
        notification.style.display = 'block';
        setTimeout(() => {
            notification.style.display = 'none';
        }, 2000);

        // Trigger confetti animation
        const confetti = document.createElement('div');
        confetti.classList.add('confetti');
        document.body.appendChild(confetti);
        setTimeout(() => {
            confetti.remove();
        }, 2000);
    });
});
