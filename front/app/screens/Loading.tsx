import {
  ConfirmContainer,
  ConfirmTargetContainer,
  ConfirmTargetText,
} from "./styled";

interface PropsType {
  method: string;
}

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
