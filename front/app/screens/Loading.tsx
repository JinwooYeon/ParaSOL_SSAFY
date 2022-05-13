import {
  ConfirmContainer,
  ConfirmTargetContainer,
  ConfirmTargetText,
} from "./styled";

interface PropsType {
  // 송금 or 충전 or 출금
  method: string;
}

// Component _ Loading
const Loading: React.FC<PropsType> = ({ method }) => {
  return (
    <ConfirmContainer>
      <ConfirmTargetContainer>
        <ConfirmTargetText>{method}중입니다~</ConfirmTargetText>
      </ConfirmTargetContainer>
    </ConfirmContainer>
  );
};

export default Loading;
