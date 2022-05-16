import styled from "styled-components";

export const FooterInfoComponent = () => {
  return (
    <InfoDiv>
      {/* 링크 주소 연결 필요 */}
      <a href="/">
        <div>Info |</div>
      </a>
      <a href="/">
        <div> About | </div>
      </a>
      <a href="/">
        <div> Contact Us</div>
      </a>
    </InfoDiv>
  );
};

const InfoDiv = styled.div``;
