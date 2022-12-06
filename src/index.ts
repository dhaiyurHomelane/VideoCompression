import { registerPlugin } from '@capacitor/core';

import type { VideoCompressionPlugin } from './definitions';

const VideoCompression = registerPlugin<VideoCompressionPlugin>(
  'VideoCompression',
  {
    web: () => import('./web').then(m => new m.VideoCompressionWeb()),
  },
);

export * from './definitions';
export { VideoCompression };
