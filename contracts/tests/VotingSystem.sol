pragma solidity ^0.8.0;

import "../ERC20.sol";
import "../ERC721.sol";

contract VotingSystem {
    struct Election {
        uint256 id;
        string reason;
        mapping(uint256 => string) candidates;
        uint256 candidatesCount;
        mapping(address => bool) voters;
        mapping(uint256 => uint256) voteCount;
        bool isActive;
    }

    mapping(uint256 => Election) public elections;
    uint256 public electionsCount;

    ERC20 public votingToken;
    ERC721 public candidateToken;

    event Voted(uint256 indexed electionId, uint256 indexed candidateId, address indexed voter);

    constructor(address _votingTokenAddress, address _candidateTokenAddress) {
        votingToken = ERC20(_votingTokenAddress);
        candidateToken = ERC721(_candidateTokenAddress);
    }

    function startElection(string memory _reason) public {
        electionsCount++;
        elections[electionsCount].id = electionsCount;
        elections[electionsCount].reason = _reason;
        elections[electionsCount].isActive = true;
    }

    function addCandidate(uint256 _electionId, string memory _name) public {
        require(elections[_electionId].isActive, "Election is not active");
        elections[_electionId].candidatesCount++;
        elections[_electionId].candidates[elections[_electionId].candidatesCount] = _name;
        candidateToken.mint(msg.sender, elections[_electionId].candidatesCount, _name);
    }

    function vote(uint256 _electionId, uint256 _candidateId) public {
        require(elections[_electionId].isActive, "Election is not active");
        require(candidateToken.ownerOf(_candidateId) != address(0), "Invalid candidate ID");
        require(!elections[_electionId].voters[msg.sender], "You have already voted");

        votingToken.transferFrom(msg.sender, address(this), 1);
        elections[_electionId].voteCount[_candidateId]++;
        elections[_electionId].voters[msg.sender] = true;

        emit Voted(_electionId, _candidateId, msg.sender);
    }

    function endElection(uint256 _electionId) public {
        require(elections[_electionId].isActive, "Election is not active");
        elections[_electionId].isActive = false;
    }
}