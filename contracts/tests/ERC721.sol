pragma solidity ^0.8.0;

contract ERC721 {
    uint256 public totalTokens;
    mapping(uint256 => address) public tokenOwners;
    mapping(address => uint256[]) public ownedTokens;
    mapping(uint256 => address) public tokenApprovals;
    mapping(address => mapping(address => bool)) public operatorApprovals;

    event Transfer(address indexed from, address indexed to, uint256 indexed tokenId);
    event Approval(address indexed owner, address indexed approved, uint256 indexed tokenId);
    event ApprovalForAll(address indexed owner, address indexed operator, bool approved);

    constructor() {
        totalTokens = 0;
    }

    function balanceOf(address _owner) external view returns (uint256) {
        return ownedTokens[_owner].length;
    }

    function ownerOf(uint256 _tokenId) external view returns (address) {
        return tokenOwners[_tokenId];
    }

    function approve(address _to, uint256 _tokenId) external {
        address owner = tokenOwners[_tokenId];
        require(msg.sender == owner || operatorApprovals[owner][msg.sender], "Not authorized");
        tokenApprovals[_tokenId] = _to;
        emit Approval(msg.sender, _to, _tokenId);
    }

    function setApprovalForAll(address _operator, bool _approved) external {
        operatorApprovals[msg.sender][_operator] = _approved;
        emit ApprovalForAll(msg.sender, _operator, _approved);
    }

    function transfer(address _to, uint256 _tokenId) external {
        address owner = tokenOwners[_tokenId];
        require(msg.sender == owner || msg.sender == tokenApprovals[_tokenId] || operatorApprovals[owner][msg.sender], "Not authorized");
        _transfer(owner, _to, _tokenId);
    }

    function transferFrom(address _from, address _to, uint256 _tokenId) external {
        address owner = tokenOwners[_tokenId];
        require(_from == owner || msg.sender == tokenApprovals[_tokenId] || operatorApprovals[owner][msg.sender], "Not authorized");
        _transfer(_from, _to, _tokenId);
    }

    function _transfer(address _from, address _to, uint256 _tokenId) internal {
        require(tokenOwners[_tokenId] == _from, "Owner mismatch");
        tokenOwners[_tokenId] = _to;
        emit Transfer(_from, _to, _tokenId);
    }

    function mint(address _to) external {
        uint256 tokenId = totalTokens + 1;
        totalTokens++;
        tokenOwners[tokenId] = _to;
        ownedTokens[_to].push(tokenId);
        emit Transfer(address(0), _to, tokenId);
    }
}