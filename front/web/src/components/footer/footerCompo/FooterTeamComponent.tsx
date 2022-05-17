import styled from "styled-components";

export const FooterTeamComponent = () => {
  return (
    <TeamDiv>
      {/* 팀 미모지로 수정해야함 */}
      <TeamImg src="footerImg.jpg" />
    </TeamDiv>
  );
};

const TeamDiv = styled.div`
  margin-bottom: 30%;
`;
const TeamImg = styled.img`
  width: 800px;
`;
