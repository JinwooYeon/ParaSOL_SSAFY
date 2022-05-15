import styled from "styled-components";

export const FooterCompo = () => {
  return (
    <>
      <FooterDiv>
        <TeamDiv>
          {/* 팀 미모지로 수정해야함 */}
          <TeamImg src="https://file2.nocutnews.co.kr/newsroom/image/2018/06/26/20180626182720664131_0_710_220.jpg" />
        </TeamDiv>
        <ImgDiv>
          <ImgBox>
            <a href="https://www.ssafy.com">
              <Img src="https://avatars.githubusercontent.com/u/84579378?s=200&v=4" />
            </a>
          </ImgBox>
          <ImgBox>
            <a href="https://lab.ssafy.com/s06-final/S06P31S101">
              <Img src="https://blog.kakaocdn.net/dn/euksHz/btrkikzsvnn/p5AdNUCmxGuZbnu4Vq98Y0/img.webp" />
            </a>
          </ImgBox>
          <ImgBox>
            <a href="https://www.notion.so/ssafy-parasol/a8a22b29d17f4ae2b73be02f6ccba63c">
              <Img src="https://blog.kakaocdn.net/dn/2CufY/btq8RTsnYaT/3EQm1A8iL54UcCzFkemVJ1/img.png" />
            </a>
          </ImgBox>
        </ImgDiv>
        <InfoDiv>
          {/* 링크 주소 연결 필요 */}
          <a href="/">
            <text>Info |</text>
          </a>
          <span
            onClick={() => {
              console.log("click");
            }}
          >
            <text> About | </text>
          </span>
          <a href="/">
            <text> Contact Us</text>
          </a>
        </InfoDiv>
        <div>@ 2022 TEAM ParaSOL</div>
      </FooterDiv>
    </>
  );
};

const FooterDiv = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 50px 10px 30px 10px;
  gap: 15px;
`;
const ImgDiv = styled.div`
  display: flex;
  flex-direction: row;
  margin: 20px;
  gap: 15px;
`;
const InfoDiv = styled.div``;
const ImgBox = styled.div`
  width: 60px;
  height: 60px;
  border-radius: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const Img = styled.img`
  width: 60px;
`;
const TeamDiv = styled.div`
  /* position: absolute; */
  /* left: 75%; */
`;
const TeamImg = styled.img`
  width: 550px;
`;
