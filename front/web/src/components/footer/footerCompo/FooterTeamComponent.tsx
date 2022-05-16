import styled from "styled-components";

export const FooterTeamComponent = () => {
  return (
    <TeamDiv>
      {/* 팀 미모지로 수정해야함 */}
      <TeamImg src="https://file2.nocutnews.co.kr/newsroom/image/2018/06/26/20180626182720664131_0_710_220.jpg" />
    </TeamDiv>
  );
};

const TeamDiv = styled.div``;
const TeamImg = styled.img`
  width: 300px;
`;
