document.addEventListener('DOMContentLoaded', () => {
  document.getElementById('btn1').addEventListener('click', () => {
    document.getElementById('features').innerHTML = '<ul>\n' +
      '    <li>Blockchain Security</li>\n' +
      '    <li>Transparent Ledger</li>\n' +
      '    <li>Decentralized Identity Verification</li>\n' +
      '    <li>Smart Contracts for Governance</li>\n' +
      '    <li>Anonymity and Privacy</li>\n' +
      '  </ul>';
  });
});


const contractAddress = '0x...';

const votingContract = new web3.eth.Contract(votingSystemABI, contractAddress);

async function startVotingEvent(electionName, startDateTime, endDateTime) {
  try {
    const accounts = await web3.eth.getAccounts();
    await votingContract.methods.startElection(electionName).send({
      from: accounts[0],
      gas: 2000000,
    });
    alert('Voting event started successfully!');
  } catch (error) {
    alert('Error starting voting event: ' + error.message);
  }
}

votingContract.events.Voted()
  .on('data', function(event) {
    console.log('Vote event:', event);
  })
  .on('error', function(error) {
    console.error('Vote event error:', error);
  });