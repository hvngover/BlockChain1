pragma solidity ^0.8.0;

contract GovVoteSmartContract {
    mapping(string => uint256) private candidateVotes;
    address public owner;

    event VoteCasted(address voter, string candidate);

    modifier onlyOwner() {
        require(msg.sender == owner, "Only the owner can call this function");
        _;
    }

    constructor() {
        owner = msg.sender;
    }

    function castVote(string memory candidate) public {
        candidateVotes[candidate]++;
        emit VoteCasted(msg.sender, candidate);
    }

    function getVotesForCandidate(string memory candidate) public view returns (uint256) {
        return candidateVotes[candidate];
    }

    function getCandidates() public view returns (string[] memory) {
        string[] memory candidates = new string[](candidateVotes.length);
        for (uint256 i = 0; i < candidateVotes.length; i++) {
            candidates[i] = candidateVotes[i];
        }
        return candidates;
    }
}