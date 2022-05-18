import styled from "styled-components";

export const FooterImageComponent = () => {
  return (
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
  );
};

const ImgDiv = styled.div`
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
  gap: 40px;
`;
const ImgBox = styled.div`
  width: 50px;
  height: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const Img = styled.img`
  width: 50px;
`;
